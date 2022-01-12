package de.united.aztube.backend.Model;


public class PollRequest {

    String deviceToken;

    public PollRequest(String code) {
        this.deviceToken = deviceToken;
    }

    public PollRequest() {}

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}
