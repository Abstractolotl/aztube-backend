package de.united.aztube.backend;


public class StatusRequest {
    String code;

    public StatusRequest(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
