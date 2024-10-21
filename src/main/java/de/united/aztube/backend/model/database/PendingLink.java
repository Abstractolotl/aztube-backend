package de.united.aztube.backend.model.database;

import de.united.aztube.backend.model.PendingLinkStatus;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "StatusDB")
public class PendingLink implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Type(type = "uuid-char")
    private UUID code;
    private String firebaseToken;
    private long timestamp;
    @Enumerated(EnumType.STRING)
    private PendingLinkStatus status;
    @Type(type = "uuid-char")
    private UUID deviceToken;
    private String deviceName;

}
