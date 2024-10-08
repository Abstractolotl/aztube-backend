package de.united.aztube.backend.model;

public class DownloadResponse {

    boolean success;
    private String error;

    public DownloadResponse() {
    }

    public DownloadResponse(boolean success, String error) {
        this.success = success;
        this.error = error;
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
}
