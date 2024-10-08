package de.united.aztube.backend.controller;


import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import de.united.aztube.backend.model.GenerateRespose;
import de.united.aztube.backend.model.StatusRequest;
import de.united.aztube.backend.model.StatusResponse;
import de.united.aztube.backend.model.RegisterRequest;
import de.united.aztube.backend.model.RegisterResponse;
import de.united.aztube.backend.database.Link;
import de.united.aztube.backend.database.LinkRepository;
import de.united.aztube.backend.database.StatusDB;
import de.united.aztube.backend.database.StatusCodeRepository;
import de.united.aztube.backend.model.*;
import de.united.aztube.backend.database.*;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@RestController
@EnableScheduling
public class BrowserExtensionController {

    int timeout = 30;

    private @Autowired StatusCodeRepository statusCodeRepository;
    private @Autowired LinkRepository linkRepository;
    private @Autowired DownloadRepository downloadRepository;
    private @Autowired FirebaseMessaging firebaseMessaging;

    @GetMapping(path = "/generate")
        public GenerateRespose generate() {

        StatusDB statusDB = new StatusDB();
        GenerateRespose generateRespose = new GenerateRespose(timeout);
        statusDB.setCode(generateRespose.getUuid());
        statusDB.setTimestamp(System.currentTimeMillis());
        statusDB.setStatus("generated");
        statusCodeRepository.save(statusDB);
        return generateRespose;
    }

    @PostMapping(path = "/browserTokenStatus")
    public BrowserTokenResponse browserTokenStatus(@RequestBody BrowserTokenRequest request){
        BrowserTokenResponse response = new BrowserTokenResponse();
        response.setTokens(new ArrayList<>());
        Iterable<Link> links = linkRepository.findAll();
        request.getTokens().forEach(token -> {
            boolean exists = StreamSupport
                    .stream(links.spliterator(), false)
                    .anyMatch(link -> link.getBrowserToken().equals(token.toString()));
            response.getTokens().add(new BrowserTokenStatus(token, exists));
        });
        return response;
    }

    @PostMapping(path = "/register")
    public @ResponseBody
    RegisterResponse register(@RequestBody RegisterRequest request) {
        StatusDB entry = statusCodeRepository.findByCode(request.getCode().toString());
        if(entry == null) {
            return new RegisterResponse(false, "no such entry", null);
        }

        if(!entry.getStatus().equals("generated")) {
            return new RegisterResponse(false, "browser token is already registered", null);
        }

        UUID deviceToken = UUID.randomUUID();
        entry.setDeviceToken(deviceToken.toString());
        entry.setDeviceName(request.getDeviceName());
        entry.setFirebaseToken(request.getFirebaseToken());
        entry.setStatus("registered");
        statusCodeRepository.save(entry);

        return new RegisterResponse(true, "", deviceToken);
    }

    @PostMapping(path = "/unregister")
    public @ResponseBody
    DownloadResponse unregister(@RequestBody PollRequest pollRequest) {
        Link link = linkRepository.findByDeviceToken(pollRequest.getDeviceToken());

        if(link == null){
            link = linkRepository.findByBrowserToken(pollRequest.getDeviceToken());
            if(link == null){
                return new DownloadResponse(false, "token not found");
            } else {
                linkRepository.deleteById(linkRepository.findByBrowserToken(pollRequest.getDeviceToken()).getId());
            }
        } else {
            linkRepository.deleteById(linkRepository.findByDeviceToken(pollRequest.getDeviceToken()).getId());
        }
        return new DownloadResponse(true, null);
    }

    @PostMapping(path = "/status")
    public @ResponseBody StatusResponse status(@RequestBody StatusRequest request) {
        statusCodeRepository.findAll().stream()
                .filter(x -> (System.currentTimeMillis() - x.getTimestamp() > (timeout * 1000)))
                .toList().forEach(x -> {
                    statusCodeRepository.deleteById(x.getId());
                    System.out.println("entry number: " + x.getId() + " timed out");});
        StatusDB statusDB = statusCodeRepository.findByCode(request.getCode());
        if(statusDB == null){
            return new StatusResponse(false, null, null, null, "no entry in database");
        }

        if(statusDB.getStatus().equals("registered")) {
            UUID browserToken = UUID.randomUUID();
            if(statusDB.getDeviceToken() == null || statusDB.getDeviceName() == null || statusDB.getDeviceName().trim().equals("")) {
                return new StatusResponse(false , null, null, null, "Integrity error");
            }
            Link link = new Link();
            link.setBrowserToken(browserToken.toString());
            link.setTimestamp(System.currentTimeMillis());
            link.setDeviceToken(statusDB.getDeviceToken());
            link.setDeviceName(statusDB.getDeviceName());
            link.setFirebaseToken(statusDB.getFirebaseToken());
            linkRepository.save(link);

            return new StatusResponse(true, statusDB.getStatus(), browserToken.toString(), statusDB.getDeviceName(), null);
        }

        StatusResponse response = new StatusResponse(true, statusDB.getStatus(), null, null, null);
        return response;
    }

    @PostMapping(path = "/download")
    public @ResponseBody
    DownloadResponse download(@RequestBody DownloadRequest request) {
        if (!(request.getQuality().equals("audio")
                ||request.getQuality().equals("144p")
                ||request.getQuality().equals("240p")
                ||request.getQuality().equals("360p")
                ||request.getQuality().equals("480p")
                ||request.getQuality().equals("720p")
                ||request.getQuality().equals("720p60")
                ||request.getQuality().equals("1080p")
                ||request.getQuality().equals("1080p60")
                ||request.getQuality().equals("1440p")
                ||request.getQuality().equals("1440p60")
                ||request.getQuality().equals("2160p")
                ||request.getQuality().equals("2160p60"))) {
            return new DownloadResponse(false, "bad quality");
        }

        Link link = linkRepository.findByBrowserToken(request.getBrowserToken());
        if(link == null){
            return new DownloadResponse(false, "browserToken not Found");
        }

        Download download = new Download();
        download.setDeviceToken(link.getDeviceToken());
        download.setTitle(request.getTitle());
        download.setQuality(request.getQuality());
        download.setVideoId(request.getVideoId());
        download.setAuthor(request.getAuthor());
        downloadRepository.save(download);

        if (StringUtils.isNotEmpty(link.getFirebaseToken())) {
            try {
                firebaseMessaging.send(Message.builder()
                        .setToken(link.getFirebaseToken())
                        .setNotification(Notification.builder()
                                .setTitle("New Download")
                                .setBody("New download is available: " + request.getTitle())
                                .build())
                        .build());
            } catch (FirebaseMessagingException e) {
                log.error("Could not send Firebase Message", e);
            }
        }

        return new DownloadResponse(true, null);
    }

    @GetMapping(path = "/poll/{deviceToken}")
    public @ResponseBody
    PollResponse poll(@PathVariable UUID deviceToken) {
        Link link = linkRepository.findByDeviceToken(deviceToken.toString());
        if(link == null) {
            StatusDB db = statusCodeRepository.findByDeviceToken(deviceToken.toString());
            if(db != null) new PollResponse(false, null, "deviceToken not ready yet");
            return new PollResponse(false, null, "deviceToken does not exist");
        }

        List<Download> downloads = downloadRepository.findAllByDeviceToken(deviceToken.toString());
        downloadRepository.findAll()
                .stream().filter(x -> (x.getDeviceToken().equals(deviceToken.toString())))
                .toList().forEach(x -> {downloadRepository.deleteById(x.getDownloadId());
                System.out.println("Download request number: " + x.getDownloadId() + " was deleted");});
        if (downloads.isEmpty()) return new PollResponse(false , downloads , "no entry in database");
        return new PollResponse(true , downloads , null);
    }


}
