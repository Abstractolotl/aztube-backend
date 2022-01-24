package de.united.aztube.backend.Database;

import java.util.UUID;

public class BrowserTokenStatus {

    private UUID browserToken;
    private boolean exists;

    public BrowserTokenStatus(UUID browserToken, boolean exists) {
        this.browserToken = browserToken;
        this.exists = exists;
    }

    public BrowserTokenStatus(){

    }

    public UUID getBrowserToken() {
        return browserToken;
    }

    public boolean isExists(){
        return exists;
    }

    public void setBrowserToken(UUID browserToken){
        this.browserToken = browserToken;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

}
