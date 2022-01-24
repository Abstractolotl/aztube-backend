package de.united.aztube.backend.Model;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class RegisterRequest {

    @NotNull private UUID code;
    @NotNull private String deviceName;

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


