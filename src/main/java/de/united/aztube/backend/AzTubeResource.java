package de.united.aztube.backend;


import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import de.united.aztube.backend.model.BrowserTokenStatus;
import de.united.aztube.backend.model.PendingLinkStatus;
import de.united.aztube.backend.model.aztube.*;
import de.united.aztube.backend.model.database.Link;
import de.united.aztube.backend.database.LinkRepository;
import de.united.aztube.backend.model.database.PendingLink;
import de.united.aztube.backend.database.StatusCodeRepository;
import de.united.aztube.backend.database.*;
import de.united.aztube.backend.model.database.Download;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@EnableScheduling
@RequiredArgsConstructor
public class AzTubeResource {

    private static final int CODE_TIMEOUT = 30;

    private final StatusCodeRepository statusCodeRepository;
    private final LinkRepository linkRepository;
    private final DownloadRepository downloadRepository;
    private final FirebaseMessaging firebaseMessaging;

    @GetMapping(path = "/generate")
    public GenerateResponse generate() {
        PendingLink statusDB = new PendingLink();
        statusDB.setCode(UUID.randomUUID());
        statusDB.setTimestamp(System.currentTimeMillis());
        statusDB.setStatus(PendingLinkStatus.GENERATED);
        statusCodeRepository.save(statusDB);

        return new GenerateResponse(true, statusDB.getCode(), 30);
    }

    @PostMapping(path = "/browserTokenStatus")
    public BrowserTokenStatusResponse browserTokenStatus(@RequestBody BrowserTokenStatusRequest request){
        List<Link> links = linkRepository.findByBrowserTokenIn(request.getTokens());

        List<BrowserTokenStatus> statuses = request.getTokens().stream()
                .map(token -> {
                    for (Link link : links) {
                        if (link.getBrowserToken().equals(token)) {
                            return new BrowserTokenStatus(token, true);
                        }
                    }
                    return new BrowserTokenStatus(token, false);
                })
                .toList();

        return new BrowserTokenStatusResponse(true, "", statuses);
    }

    @PostMapping(path = "/register")
    public RegisterResponse register(@RequestBody RegisterRequest request) {
        PendingLink entry = statusCodeRepository.findByCode(request.getCode());

        if(entry == null) {
            return new RegisterResponse(false, "no such entry", null);
        }
        if(entry.getStatus() != PendingLinkStatus.GENERATED) {
            return new RegisterResponse(false, "browser token is already registered", null);
        }

        entry.setDeviceToken(UUID.randomUUID());
        entry.setDeviceName(request.getDeviceName());
        entry.setFirebaseToken(request.getFirebaseToken());
        entry.setStatus(PendingLinkStatus.REGISTERED);
        statusCodeRepository.save(entry);

        return new RegisterResponse(true, "", entry.getDeviceToken());
    }

    @PostMapping(path = "/unregister")
    public DownloadResponse unregister(@RequestBody PollRequest pollRequest) {
        Link link = linkRepository.findByDeviceToken(pollRequest.getDeviceToken());
        if(link == null) {
            link = linkRepository.findByBrowserToken(pollRequest.getDeviceToken());
        }
        if(link == null){
            return new DownloadResponse(false, "token not found");
        }

        linkRepository.delete(link);
        return new DownloadResponse(true, null);
    }

    @PostMapping(path = "/status")
    public StatusResponse status(@RequestBody StatusRequest request) {
        StatusResponse response = new StatusResponse();

        PendingLink pendingLink = statusCodeRepository.findByCode(request.getCode());
        if(pendingLink == null){
            response.setSuccess(false);
            response.setError("no entry in database");
            return response;
        }

        if(pendingLink.getStatus() != PendingLinkStatus.REGISTERED) {
            response.setSuccess(true);
            response.setStatus(pendingLink.getStatus());
            return response;
        }

        if(pendingLink.getDeviceToken() != null || StringUtils.isNotBlank(pendingLink.getDeviceName())) {
            response.setSuccess(false);
            response.setError("Integrity error");
            return response;
        }

        Link link = new Link();
        link.setBrowserToken(UUID.randomUUID());
        link.setTimestamp(System.currentTimeMillis());
        link.setDeviceToken(pendingLink.getDeviceToken());
        link.setDeviceName(pendingLink.getDeviceName());
        link.setFirebaseToken(pendingLink.getFirebaseToken());
        linkRepository.save(link);
        statusCodeRepository.delete(pendingLink);

        response.setSuccess(true);
        response.setStatus(pendingLink.getStatus());
        response.setBrowserToken(link.getBrowserToken());
        response.setDeviceName(link.getDeviceName());
        return response;
    }

//    @Scheduled(fixedDelay = 1000 * 60 * 60) //Every hour
//    public void cleanStatusCodes() {
//        statusCodeRepository.deleteAllByTimestampLessThan(System.currentTimeMillis() - CODE_TIMEOUT * 1000L);
//    }

    @PostMapping(path = "/download")
    public @ResponseBody
    DownloadResponse download(@RequestBody DownloadRequest request) {
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

        if (StringUtils.isNotBlank(link.getFirebaseToken())) {
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
        Link link = linkRepository.findByDeviceToken(deviceToken);
        if(link == null) {
            return new PollResponse(false, "deviceToken does not exist", null);
        }

        List<Download> downloads = downloadRepository.findAllByDeviceToken(deviceToken);
        if (downloads.isEmpty()) {
            return new PollResponse(false ,"no entry in database", null);
        }

        downloadRepository.deleteAll(downloads);
        return new PollResponse(true, null, downloads);
    }

}
