package de.united.aztube.backend.model.aztube;

import de.united.aztube.backend.model.BrowserTokenStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BrowserTokenStatusResponse {

    private boolean success;
    private String error;
    private List<BrowserTokenStatus> tokens;

}
