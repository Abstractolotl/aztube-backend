package de.united.aztube.backend;

import de.united.aztube.backend.database.RegisterDB;
import org.springframework.beans.factory.annotation.Autowired;

public class StatusResponse {
    String status;
    String code;

    public StatusResponse() {}

    @Autowired
    RegisterDB registerDB;

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return registerDB.checkStatus(code);
    }
}
