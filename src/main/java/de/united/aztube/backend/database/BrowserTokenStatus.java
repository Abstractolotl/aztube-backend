package de.united.aztube.backend.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrowserTokenStatus {

    private UUID browserToken;
    private boolean exists;

}
