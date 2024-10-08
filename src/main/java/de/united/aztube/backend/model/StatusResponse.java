package de.united.aztube.backend.model;

public class StatusResponse {

    private boolean success;
    private String error;

    private String status;
    private String browserToken;
    private String deviceName;

    public StatusResponse(boolean success, String status, String browserToken, String deviceName, String error) {
        this.status = status;
        this.browserToken = browserToken;
        this.deviceName = deviceName;
        this.success = success;
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

    public String getBrowserToken() {
        return browserToken;
    }

    public void setBrowserToken(String browserToken) {
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