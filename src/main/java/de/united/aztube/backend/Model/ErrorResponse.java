package de.united.aztube.backend.Model;

public class ErrorResponse {

    private boolean success;
    private String error;
    private Exception exception;

    public ErrorResponse() {
    }

    public ErrorResponse(String error) {
        this.success = false;
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

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

}
