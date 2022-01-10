package de.united.aztube.backend;

import java.util.UUID;

public class CodeGenerator {
    boolean success = true;
    String uuid;
    int timeout = 30;


    public CodeGenerator() {
        this.uuid = UUID.randomUUID().toString();
        this.timeout = timeout;
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
