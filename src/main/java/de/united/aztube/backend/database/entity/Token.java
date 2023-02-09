package de.united.aztube.backend.database.entity;

import de.united.aztube.backend.database.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "extensions")
public class Token implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String code;
    private long timestamp;
    private Status status;

    private String deviceToken;
    private String deviceName;

}
