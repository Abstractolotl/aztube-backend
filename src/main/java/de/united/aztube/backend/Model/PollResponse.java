package de.united.aztube.backend.Model;

import de.united.aztube.backend.database.Download;

import java.util.List;

public class PollResponse {

    boolean success;
    private String error;
    private List<Download> download;

    public PollResponse() {
    }

    public PollResponse(boolean success, List<Download> download, String error) {
        this.success = success;
        this.error = error;
        this.download = download;
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

    public List<Download> getDownload() {
        return download;
    }

    public void setDownload(List<Download> download) {
        this.download = download;
    }
}
