package de.united.aztube.backend.model.aztube;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class BrowserTokenStatusRequest {

    public List<UUID> tokens;

}
