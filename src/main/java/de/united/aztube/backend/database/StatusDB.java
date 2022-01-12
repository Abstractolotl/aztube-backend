package de.united.aztube.backend.database;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "StatusDB")
public class StatusDB implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String code;
    private long timestamp;
    private String status;

    private String deviceToken;
    private String deviceName;

    public StatusDB() {

    }

    public StatusDB(int id, String code, long timestamp, String status) {
        this.id=id;
        this.code = code;
        this.timestamp = timestamp;
        this.status = status;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
