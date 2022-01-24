package de.united.aztube.backend.Model;


import javax.validation.constraints.NotNull;

public class PollRequest {

    @NotNull
    private String deviceToken;

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
