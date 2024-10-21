package de.united.aztube.backend.model.aztube;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class PollRequest {

    private UUID deviceToken;

}
