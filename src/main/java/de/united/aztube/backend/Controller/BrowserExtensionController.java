package de.united.aztube.backend.Controller;

import de.united.aztube.backend.CodeGenerator;
import de.united.aztube.backend.Model.RegisterRequest;
import de.united.aztube.backend.Model.RegisterResponse;
import de.united.aztube.backend.Model.StatusRequest;
import de.united.aztube.backend.Model.StatusResponse;
import de.united.aztube.backend.database.Link;
import de.united.aztube.backend.database.LinkRepository;
import de.united.aztube.backend.database.StatusDB;
import de.united.aztube.backend.database.StatusCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@EnableScheduling
@RequestMapping(path = "api/v1/qr")
public class BrowserExtensionController {

    private @Autowired StatusCodeRepository repository;
    private @Autowired LinkRepository linkRepository;

    @GetMapping(path = "/generate")
    public CodeGenerator generate() {
        StatusDB statusDB = new StatusDB();
        CodeGenerator codeGenerator = new CodeGenerator();
        statusDB.setCode(codeGenerator.getUuid());
        Date date = new Date();
        statusDB.setTimestamp(System.currentTimeMillis());
        statusDB.setStatus("generated");
        repository.save(statusDB);
        return codeGenerator;
    }

    @PostMapping(path = "/register")
    public @ResponseBody
    RegisterResponse register(@RequestBody RegisterRequest request) {
        StatusDB entry = repository.findByCode(request.getCode().toString());
        if(entry == null) {
            //TODO:
            return null;
        }

        if(!entry.getStatus().equals("generated")) {
            //TODO:
            return null;
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
            return null;
        }

        if(statusDB.getStatus().equals("registered")) {
            UUID browserToken = UUID.randomUUID();
            if(statusDB.getDeviceToken() == null || statusDB.getDeviceName() == null || statusDB.getDeviceName().trim().equals("")) {
                //TODO: Integrity error
                return null;
            }
            Link link = new Link(browserToken.toString(), statusDB.getDeviceToken(), statusDB.getDeviceName(), System.currentTimeMillis());
            linkRepository.save(link);

            return new StatusResponse(statusDB.getStatus(), browserToken, statusDB.getDeviceName());
        };

        StatusResponse response = new StatusResponse(statusDB.getStatus(), null, "");
        return response;
    }

    @Scheduled(fixedDelay = 1000)
    @GetMapping(path = "/checkTimeout")
    public void checkTimeout() {
        List<StatusDB> statusDB = repository.findAll().
                stream().filter(x -> (System.currentTimeMillis() - x.getTimestamp() > 30000)
                        && x.getStatus().equals("generated"))
                .collect(Collectors.toList());

        statusDB.forEach(x -> {repository.deleteById(x.getId());
            System.out.println("entry number: " + x.getId() + " timed out");});
    }


}