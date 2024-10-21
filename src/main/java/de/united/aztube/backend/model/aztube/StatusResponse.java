package de.united.aztube.backend.model.aztube;

import de.united.aztube.backend.model.PendingLinkStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class StatusResponse {

    private boolean success;
    private PendingLinkStatus status;

    private UUID browserToken;
    private String deviceName;
    private String error;

}