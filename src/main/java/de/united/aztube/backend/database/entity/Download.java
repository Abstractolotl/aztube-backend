package de.united.aztube.backend.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Downloads")
public class Download {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int downloadId;
    private String deviceToken;
    private String videoId;
    private String quality;
    private String title;
    private String author;


}

