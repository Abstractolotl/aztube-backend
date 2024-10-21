package de.united.aztube.backend.model.aztube;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class RegisterResponse {

    private boolean success;
    private String error;
    private UUID deviceToken;

}