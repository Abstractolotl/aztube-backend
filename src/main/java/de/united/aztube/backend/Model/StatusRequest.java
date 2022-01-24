package de.united.aztube.backend.Model;


import javax.validation.constraints.NotNull;

public class StatusRequest {

    @NotNull private String code;

    public StatusRequest(String code) {
        this.code = code;
    }

    public StatusRequest() {

    }

    public String getCode() {
        return code;
    }
}
