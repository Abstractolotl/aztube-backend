package de.united.aztube.backend.Model;

import java.util.UUID;

public class RegisterResponse {
    private boolean success;
    private String error;

    private UUID deviceToken;

    public RegisterResponse() {
    }

    public RegisterResponse(boolean success, String error, UUID deviceToken) {
        this.success = success;
        this.error = error;
        this.deviceToken = deviceToken;
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

    public UUID getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(UUID deviceToken) {
        this.deviceToken = deviceToken;
    }
}