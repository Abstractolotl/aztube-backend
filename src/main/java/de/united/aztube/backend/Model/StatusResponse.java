package de.united.aztube.backend.Model;

import java.util.UUID;

public class StatusResponse {

    private String status;
    private UUID browserToken;
    private String deviceName;

    public StatusResponse(String status, UUID browserToken, String deviceName) {
        this.status = status;
        this.browserToken = browserToken;
        this.deviceName = deviceName;
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
