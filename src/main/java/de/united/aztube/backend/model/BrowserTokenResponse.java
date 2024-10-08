package de.united.aztube.backend.model;

import de.united.aztube.backend.database.BrowserTokenStatus;

import java.util.List;

public class BrowserTokenResponse {

    private boolean success;
    private String error;

    private List<BrowserTokenStatus> tokens;

    public BrowserTokenResponse(boolean success, String error, List<BrowserTokenStatus> tokens) {
        this.success = success;
        this.error = error;
        this.tokens = tokens;
    }

    public BrowserTokenResponse(){

    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<BrowserTokenStatus> getTokens() {
        return tokens;
    }

    public void setTokens(List<BrowserTokenStatus> tokens) {
        this.tokens = tokens;
    }
}
