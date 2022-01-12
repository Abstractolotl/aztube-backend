package de.united.aztube.backend.Model;

import java.util.UUID;

public class RegisterRequest {

    private UUID code;

    public RegisterRequest(UUID code) {
        this.code = code;
    }

    public RegisterRequest() {

    }

    public UUID getCode() {
        return code;
    }

    public void setCode(UUID code) {
        this.code = code;
    }

}


