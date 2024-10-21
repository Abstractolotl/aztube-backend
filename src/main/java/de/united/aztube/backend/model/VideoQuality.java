package de.united.aztube.backend.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum VideoQuality {

    AUDIO("audio"),
    P144("144p"),
    P240("240p"),
    P360("360p"),
    P480("480p"),
    P720("720p"),
    P720_60("720p60"),
    P1080("1080p"),
    P1080_60("1080p60"),
    P1440("1440p"),
    P1440_60("1440p60"),
    P2160("2160p"),
    P2160_60("2160p60");

    private final String quality;
}
