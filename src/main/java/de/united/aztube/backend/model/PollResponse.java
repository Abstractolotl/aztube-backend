package de.united.aztube.backend.model;

import de.united.aztube.backend.database.Download;

import java.util.List;

public class PollResponse {

    boolean success;
    private String error;
    private List<Download> downloads;

    public PollResponse() {
    }

    public PollResponse(boolean success, List<Download> downloads, String error) {
        this.success = success;
        this.error = error;
        this.downloads = downloads;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Download> getDownloads() {
        return downloads;
    }

    public void setDownloads(List<Download> downloads) {
        this.downloads = downloads;
    }
}
