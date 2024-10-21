package de.united.aztube.backend.model.aztube;

import de.united.aztube.backend.model.database.Download;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PollResponse {

    boolean success;
    private String error;
    private List<Download> downloads;
}
