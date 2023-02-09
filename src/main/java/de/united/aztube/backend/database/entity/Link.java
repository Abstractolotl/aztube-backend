package de.united.aztube.backend.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Links")
public class Link {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String browserToken;
    private String deviceToken;
    private String deviceName;
    private long timestamp;

    public Link(String browserToken, String deviceToken, String deviceName, long timestamp) {
        this.browserToken = browserToken;
        this.deviceToken = deviceToken;
        this.deviceName = deviceName;
        this.timestamp = timestamp;
    }

}
