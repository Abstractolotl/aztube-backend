package de.united.aztube.backend.Controller;


import de.united.aztube.backend.Model.GenerateRespose;
import de.united.aztube.backend.Model.StatusRequest;
import de.united.aztube.backend.Model.StatusResponse;
import de.united.aztube.backend.Model.RegisterRequest;
import de.united.aztube.backend.Model.RegisterResponse;
import de.united.aztube.backend.database.Link;
import de.united.aztube.backend.database.LinkRepository;
import de.united.aztube.backend.database.StatusDB;
import de.united.aztube.backend.database.StatusCodeRepository;
import de.united.aztube.backend.Model.*;
import de.united.aztube.backend.database.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@EnableScheduling
public class BrowserExtensionController {

    int timeout = 30;

    private @Autowired StatusCodeRepository repository;
    private @Autowired LinkRepository linkRepository;
    private @Autowired DownloadRepository downloadRepository;

    @GetMapping(path = "/generate")
        public GenerateRespose getCode() {

        StatusDB statusDB = new StatusDB();
        GenerateRespose generateRespose = new GenerateRespose(timeout);
        statusDB.setCode(generateRespose.getUuid());
        statusDB.setTimestamp(System.currentTimeMillis());
        statusDB.setStatus("generated");
        repository.save(statusDB);
        return generateRespose;
    }

    @PostMapping(path = "/register")
    public @ResponseBody
    RegisterResponse register(@RequestBody RegisterRequest request) {
        StatusDB entry = repository.findByCode(request.getCode().toString());
        if(entry == null) {
            //TODO:
            return new RegisterResponse(false, "no such entry", null);
        }

        if(!entry.getStatus().equals("generated")) {
            //TODO:
            return new RegisterResponse(false, "browser token is already registered", null);
        }

        UUID deviceToken = UUID.randomUUID();
        entry.setDeviceToken(deviceToken.toString());
        entry.setDeviceName(request.getDeviceName());
        entry.setStatus("registered");
        repository.save(entry);

        RegisterResponse response = new RegisterResponse(true, "", deviceToken);
        return response;
    }

    @PostMapping(path = "/status")
    public @ResponseBody StatusResponse status(@RequestBody StatusRequest request) {
        StatusDB statusDB = repository.findByCode(request.getCode());
        if(statusDB == null){
            //TODO:
            return new StatusResponse(false, null, null, null, "no entry in database");
        }

        if(statusDB.getStatus().equals("registered")) {
            UUID browserToken = UUID.randomUUID();
            if(statusDB.getDeviceToken() == null || statusDB.getDeviceName() == null || statusDB.getDeviceName().trim().equals("")) {
                //TODO: Integrity error
                return new StatusResponse(false , null, null, null, "Integrity error");
            }
            Link link = new Link(browserToken.toString(), statusDB.getDeviceToken(), statusDB.getDeviceName(), System.currentTimeMillis());
            linkRepository.save(link);

            return new StatusResponse(true, statusDB.getStatus(), browserToken.toString(), statusDB.getDeviceName(), null);
        }

        StatusResponse response = new StatusResponse(true, statusDB.getStatus(), null, "", null);
        return response;
    }

    @PostMapping(path = "/download")
    public @ResponseBody
    DownloadResponse download(@RequestBody DownloadRequest request) {
        Download download = new Download();
        download.setDeviceToken(linkRepository.findByBrowserToken(request.getBrowserToken()).getDeviceToken());
        download.setTitle(request.getFilename());
        download.setQuality(request.getQuality());
        download.setVideoID(request.getVideoID());
        download.setAuthor(request.getAuthor());
        downloadRepository.save(download);
        return new DownloadResponse(true, null);
    }

    @PostMapping(path = "/poll")
    public @ResponseBody
    List<Download> download(@RequestBody PollRequest request) {
        List<Download> downloads = downloadRepository.findAllByDeviceToken(request.getDeviceToken());
        return downloads;
    }

    @Scheduled(fixedDelay = 1000)
    @GetMapping(path = "/checkTimeout")
    public void checkTimeout() {
        List<StatusDB> statusDB = repository.findAll().
                stream().filter(x -> (System.currentTimeMillis() - x.getTimestamp() > (timeout * 1000)))
                .collect(Collectors.toList());

        statusDB.forEach(x -> {repository.deleteById(x.getId());
            System.out.println("entry number: " + x.getId() + " timed out");});
    }


}