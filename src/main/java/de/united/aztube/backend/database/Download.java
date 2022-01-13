package de.united.aztube.backend.database;

import javax.persistence.*;

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

    public Download() {

    }

    public Download(int downloadId, String deviceToken, String videoId, String quality, String title, String author) {
        this.downloadId = downloadId;
        this.deviceToken = deviceToken;
        this.videoId = videoId;
        this.quality = quality;
        this.title = title;
        this.author = author;
    }

    public int getDownloadId() {
        return downloadId;
    }

    public void setDownloadId(int downloadId) {
        this.downloadId = downloadId;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
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

