package de.united.aztube.backend.model;

import lombok.Data;

import java.util.UUID;

@Data
public class RegisterRequest {

    private UUID code;
    private String deviceName;
    private String firebaseToken;

}


