package de.united.aztube.backend.Model;

public class DownloadRequest {

    private String browserToken;
    private String videoID;
    private String quality;
    private String filename;

    public DownloadRequest() {
    }

    public DownloadRequest(String browserToken, String videoID, String quality, String filename) {
        this.browserToken = browserToken;
        this.videoID = videoID;
        this.quality = quality;
        this.filename = filename;
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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
