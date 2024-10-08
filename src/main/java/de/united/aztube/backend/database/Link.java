package de.united.aztube.backend.database;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Links")
public class Link {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String browserToken;
    private String deviceToken;
    private String deviceName;
    private String firebaseToken;
    private long timestamp;
}
