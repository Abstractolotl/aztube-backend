package de.united.aztube.backend.Model;

public class DownloadRequest {

    private String browserToken;
    private String videoID;
    private String quality;
    private String title;
    private String author;

    public DownloadRequest() {
    }

    public DownloadRequest(String browserToken, String videoID, String quality, String title) {
        this.browserToken = browserToken;
        this.videoID = videoID;
        this.quality = quality;
        this.title = title;
    }

    public String getBrowserToken() {
        return browserToken;
    }

    public void setBrowserToken(String browserToken) {
        this.browserToken = browserToken;
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

    public void setTitle(String filename) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
