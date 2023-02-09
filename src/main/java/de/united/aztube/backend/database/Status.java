package de.united.aztube.backend.database;

public enum Status {

    GENERATED,
    DOWNLOADING,
    DOWNLOADED,
    ERROR,
    DELETED,
    EXPIRED,

    REQUESTED,
    ACCEPTED,
    REJECTED,
    CANCELLED,

    REGISTERED;

}
