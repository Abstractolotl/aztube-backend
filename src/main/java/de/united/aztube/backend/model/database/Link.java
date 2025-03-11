package de.united.aztube.backend.model.database;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "Links")
public class Link {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Type(type = "uuid-char")
    private UUID browserToken;
    @Type(type = "uuid-char")
    private UUID deviceToken;
    private String deviceName;
    private String firebaseToken;
    private long timestamp;
    private long lastUsed;

}
