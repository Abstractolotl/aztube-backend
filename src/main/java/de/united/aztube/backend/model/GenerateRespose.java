package de.united.aztube.backend.model;

import java.util.UUID;

public class GenerateRespose {
    boolean success = true;
    String uuid;
    int timeout = 30;


    public GenerateRespose() {
    }

    public GenerateRespose(int timeout) {
        this.timeout = timeout;
        this.uuid = UUID.randomUUID().toString();
    }

    public String getUuid() {
        return uuid;
    }

    public int getTimeout() {
        return timeout;
    }

    public boolean isSuccess() {
        return success;
    }
}
