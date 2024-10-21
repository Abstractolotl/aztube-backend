package de.united.aztube.backend.model.aztube;

import de.united.aztube.backend.model.VideoQuality;
import lombok.Data;

import java.util.UUID;

@Data
public class DownloadRequest {

    private UUID browserToken;
    private String videoId;
    private VideoQuality quality;
    private String title;
    private String author;

}
