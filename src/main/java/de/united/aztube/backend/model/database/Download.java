package de.united.aztube.backend.model.database;

import de.united.aztube.backend.model.VideoQuality;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "Downloads")
public class Download {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int downloadId;
    @Type(type = "uuid-char")
    private UUID deviceToken;
    private String videoId;
    @Enumerated(EnumType.STRING)
    private VideoQuality quality;
    private String title;
    private String author;

}

