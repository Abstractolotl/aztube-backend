package de.united.aztube.backend.Controller;

import de.united.aztube.backend.CodeGenerator;
import de.united.aztube.backend.StatusRequest;
import de.united.aztube.backend.StatusResponse;
import de.united.aztube.backend.database.StatusDB;
import de.united.aztube.backend.database.StatusCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@EnableScheduling
@RequestMapping(path = "api/v1/qr")
public class Controller {

    @Autowired
    StatusCodeRepository repository;

    @GetMapping(path = "/generate")
    public CodeGenerator getCode() {
        StatusDB statusDB = new StatusDB();
        CodeGenerator codeGenerator = new CodeGenerator();
        statusDB.setCode(codeGenerator.getUuid());
        Date date = new Date();
        statusDB.setTimestamp(System.currentTimeMillis());
        statusDB.setStatus("generated");
        repository.save(statusDB);
        return codeGenerator;
    }

    @PostMapping(path = "/status")
    public @ResponseBody
    StatusResponse getResponse(@RequestBody StatusRequest request) {
        StatusDB statusDB = repository.findByCode(request.getCode());
        StatusResponse response = new StatusResponse(statusDB.getStatus());
        return response;
    }

    @Scheduled(fixedDelay = 1000)
    @GetMapping(path = "/checkTimeout")
    public void checkTimestamp() {
        List<StatusDB> statusDB = repository.findAll().
                stream().filter(x -> (System.currentTimeMillis() - x.getTimestamp() > 30000))
                .collect(Collectors.toList());

        statusDB.forEach(x -> {repository.deleteById(x.getId());});
    }


}