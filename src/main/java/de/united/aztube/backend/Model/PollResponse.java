package de.united.aztube.backend.Model;

public class PollResponse {

    boolean success;
    private String error;
    private DownloadRequest[] downloadRequests;

    public PollResponse() {
    }

    public PollResponse(boolean success, DownloadRequest[] downloadRequests, String error) {
        this.success = success;
        this.error = error;
        this.downloadRequests = downloadRequests;
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

    public DownloadRequest[] getDownloadRequests() {
        return downloadRequests;
    }

    public void setDownloadRequests(DownloadRequest[] downloadRequests) {
        this.downloadRequests = downloadRequests;
    }
}
