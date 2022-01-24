package de.united.aztube.backend.Model;

import javax.validation.constraints.NotNull;

public class DownloadRequest {

    @NotNull private String browserToken;
    @NotNull private String videoId;
    @NotNull private VideoQuality quality;
    private String title;
    private String author;

    public DownloadRequest() {
    }

    public DownloadRequest(String browserToken, String videoId, VideoQuality quality, String title, String author) {
        this.browserToken = browserToken;
        this.videoId = videoId;
        this.quality = quality;
        this.title = title;
        this.author = author;

    }

    public String getBrowserToken() {
        return browserToken;
    }

    public void setBrowserToken(String browserToken) {
        this.browserToken = browserToken;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public VideoQuality getQuality() {
        return quality;
    }

    public void setQuality(VideoQuality quality) {
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
