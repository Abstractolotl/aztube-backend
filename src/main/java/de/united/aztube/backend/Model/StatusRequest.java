package de.united.aztube.backend.Model;


public class StatusRequest {

    String code;

    public StatusRequest(String code) {
        this.code = code;
    }

    public StatusRequest() {

    }

    public String getCode() {
        return code;
    }
}
