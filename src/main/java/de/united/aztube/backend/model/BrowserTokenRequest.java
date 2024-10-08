package de.united.aztube.backend.model;

import java.util.List;
import java.util.UUID;

public class BrowserTokenRequest {

    public List<UUID> tokens;

    public BrowserTokenRequest(List<UUID> tokens) {
        this.tokens = tokens;
    }

    public BrowserTokenRequest() {
    }

    public List<UUID> getTokens() {
        return tokens;
    }

    public void setTokens(List<UUID> tokens) {
        this.tokens = tokens;
    }
}
