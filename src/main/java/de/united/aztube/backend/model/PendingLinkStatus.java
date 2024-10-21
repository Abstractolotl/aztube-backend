package de.united.aztube.backend.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PendingLinkStatus {

    GENERATED("generated"),
    REGISTERED("registered"),;

    private final String value;

}
