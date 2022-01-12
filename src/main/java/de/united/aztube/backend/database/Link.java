package de.united.aztube.backend.database;

import javax.persistence.*;

@Entity
@Table(name = "Links")
public class Link {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String browserToken;
    private String deviceToken;
    private String deviceName;
    private long timestamp;

    public Link(String browserToken, String deviceToken, String deviceName, long timestamp) {
        this.browserToken = browserToken;
        this.deviceToken = deviceToken;
        this.deviceName = deviceName;
        this.timestamp = timestamp;
    }

    public Link() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrowserToken() {
        return browserToken;
    }

    public void setBrowserToken(String browserToken) {
        this.browserToken = browserToken;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
