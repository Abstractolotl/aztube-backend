package de.united.aztube.backend.database;

import javax.persistence.*;

@Entity
@Table(name = "Downloads")
public class Download {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String deviceToken;
    private String videoID;
    private String quality;
    private String filename;

    public Download() {
    }

    public Download(int id, String deviceToken, String videoID, String quality, String filename) {
        this.id = id;
        this.deviceToken = deviceToken;
        this.videoID = videoID;
        this.quality = quality;
        this.filename = filename;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getVideoID() {
        return videoID;
    }

    public void setVideoID(String videoID) {
        this.videoID = videoID;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}

