package de.united.aztube.backend.model;


public class PollRequest {

    String deviceToken;

    public PollRequest(String code) {
        this.deviceToken = code;
    }

    public PollRequest() {}

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}
