package de.united.aztube.backend.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DownloadRequest {

    private String browserToken;
    private String videoId;
    private String quality;
    private String title;
    private String author;

}
