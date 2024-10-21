package de.united.aztube.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class BrowserTokenStatus {

    private UUID browserToken;
    private boolean exists;

}
