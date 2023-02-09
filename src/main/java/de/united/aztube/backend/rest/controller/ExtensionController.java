package de.united.aztube.backend.rest.controller;

import de.united.aztube.backend.database.BrowserTokenStatus;
import de.united.aztube.backend.database.Status;
import de.united.aztube.backend.database.entity.Token;
import de.united.aztube.backend.database.entity.Link;
import de.united.aztube.backend.database.repository.TokenRepository;
import de.united.aztube.backend.database.repository.LinkRepository;
import de.united.aztube.backend.model.request.BrowserTokenRequest;
import de.united.aztube.backend.model.response.BrowserTokenResponse;
import de.united.aztube.backend.model.response.GenerateResponse;
import de.united.aztube.backend.rest.api.ExtensionAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class ExtensionController implements ExtensionAPI {

    @Value("${extension.timeout:30}")
    private long timeout = 30;

    private final TokenRepository tokenRepository;
    private final LinkRepository linkRepository;

    @Autowired
    public ExtensionController(TokenRepository tokenRepository, LinkRepository linkRepository) {
        this.tokenRepository = tokenRepository;
        this.linkRepository = linkRepository;
    }

    @Override
    public GenerateResponse generate() {
        Token token = new Token();

        token.setCode(UUID.randomUUID().toString());
        token.setTimestamp(System.currentTimeMillis());
        token.setStatus(Status.GENERATED);

        tokenRepository.save(token);

        return new GenerateResponse(true, token.getCode(), timeout);
    }

    @Override
    public BrowserTokenResponse status(BrowserTokenRequest request) {
        List<String> tokens = request.getTokens().stream().map(UUID::toString).toList();
        List<Link> links = linkRepository.findAllByBrowserTokenIn(tokens);

        List<BrowserTokenStatus> statuses = tokens.stream().map(token -> {
            boolean exists = links.stream().anyMatch(link -> link.getBrowserToken().equals(token));
            return new BrowserTokenStatus(UUID.fromString(token), exists);
        }).toList();

        return BrowserTokenResponse.builder()
                .success(true)
                .error(null)
                .tokens(statuses)
                .build();
    }
}
