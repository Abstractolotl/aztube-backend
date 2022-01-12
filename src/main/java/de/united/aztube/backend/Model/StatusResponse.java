package de.united.aztube.backend.Model;

import java.util.UUID;

public class StatusResponse {

    private boolean success;
    private String error;

    private String status;
    private UUID browserToken;
    private String deviceName;

    public StatusResponse(String status, UUID browserToken, String deviceName, String error) {
        this.status = status;
        this.browserToken = browserToken;
        this.deviceName = deviceName;
        this.success = true;
        this.error = error;
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

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getBrowserToken() {
        return browserToken;
    }

    public void setBrowserToken(UUID browserToken) {
        this.browserToken = browserToken;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getStatus() {
        return this.status;
    }
}