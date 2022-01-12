package de.united.aztube.backend.Model;

import java.util.UUID;

public class RegisterRequest {

    private UUID code;
    private String deviceName;

    public RegisterRequest(UUID code, String deviceName) {
        this.code = code;
        this.deviceName = deviceName;
    }

    public RegisterRequest() {

    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public UUID getCode() {
        return code;
    }

    public void setCode(UUID code) {
        this.code = code;
    }

}


