package de.united.aztube.backend.model.aztube;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DownloadResponse {

    boolean success;
    private String error;

}
