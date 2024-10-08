package de.united.aztube.backend.database;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "StatusDB")
public class StatusDB implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String code;
    private String firebaseToken;
    private long timestamp;
    private String status;

    private String deviceToken;
    private String deviceName;

}
