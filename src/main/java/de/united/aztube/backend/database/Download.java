package de.united.aztube.backend.database;

import javax.persistence.*;

@Entity
@Table(name = "Downloads")
public class Download {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int downloadID;
    private String deviceToken;
    private String videoID;
    private String quality;
    private String title;
    private String author;

    public Download() {

    }

    public Download(int downloadID, String deviceToken, String videoID, String quality, String title) {
        this.downloadID = downloadID;
        this.deviceToken = deviceToken;
        this.videoID = videoID;
        this.quality = quality;
        this.title = title;
    }

    public int getDownloadID() {
        return downloadID;
    }

    public void setDownloadID(int id) {
        this.downloadID = id;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

