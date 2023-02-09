package de.united.aztube.backend.rest.controller;

import de.united.aztube.backend.database.Status;
import de.united.aztube.backend.database.entity.Link;
import de.united.aztube.backend.database.entity.Token;
import de.united.aztube.backend.database.repository.TokenRepository;
import de.united.aztube.backend.database.repository.LinkRepository;
import de.united.aztube.backend.model.request.PollRequest;
import de.united.aztube.backend.model.request.RegisterRequest;
import de.united.aztube.backend.model.request.StatusRequest;
import de.united.aztube.backend.model.response.GeneralResponse;
import de.united.aztube.backend.model.response.RegisterResponse;
import de.united.aztube.backend.model.response.StatusResponse;
import de.united.aztube.backend.rest.api.DeviceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
public class DeviceController implements DeviceAPI {

    private final TokenRepository tokenRepository;
    private final LinkRepository linkRepository;

    @Autowired
    public DeviceController(TokenRepository tokenRepository, LinkRepository linkRepository) {
        this.tokenRepository = tokenRepository;
        this.linkRepository = linkRepository;
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {
        Token token = tokenRepository.findByCode(request.getCode().toString());
        if (token == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Your code is invalid");
        }

        if(!token.getStatus().equals(Status.GENERATED)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Already registered");
        }

        token.setDeviceName(request.getDeviceName());
        token.setDeviceToken(UUID.randomUUID().toString());
        token.setStatus(Status.REGISTERED);
        tokenRepository.save(token);

        return RegisterResponse.builder()
                .success(true).error(null)
                .deviceToken(UUID.fromString(token.getDeviceToken()))
                .build();
    }

    @Override
    public GeneralResponse unregister(PollRequest request) {
        Link link = linkRepository.findByDeviceToken(request.getDeviceToken());

        if(link == null){
            link = linkRepository.findByBrowserToken(request.getDeviceToken());
            if(link == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such device");
            } else {
                linkRepository.deleteById(linkRepository.findByBrowserToken(request.getDeviceToken()).getId());
            }
        } else {
            linkRepository.deleteById(linkRepository.findByDeviceToken(request.getDeviceToken()).getId());
        }

        return GeneralResponse.builder()
                .success(true)
                .error(null).build();
    }

    @Override
    public StatusResponse status(StatusRequest request) {
        Token token = tokenRepository.findByCode(request.getCode());
        if(token == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such code");
        }

        if (token.getStatus().equals(Status.REGISTERED)) {
            UUID browserToken = UUID.randomUUID();
            if(token.getDeviceToken() == null || token.getDeviceName() == null || token.getDeviceName().trim().equals("")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Integrity error");
            }

            Link link = new Link(browserToken.toString(), token.getDeviceToken(), token.getDeviceName(), System.currentTimeMillis());
            linkRepository.save(link);

            return StatusResponse.builder()
                    .success(true).error(null)
                    .status(token.getStatus().toString())
                    .deviceName(token.getDeviceName())
                    .browserToken(browserToken.toString())
                    .build();
        }

        return StatusResponse.builder()
                .success(true).error(null)
                .status(token.getStatus().toString())
                .deviceName(null).browserToken(null)
                .build();
    }
}
