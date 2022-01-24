package de.united.aztube.backend.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum VideoQuality {

    @JsonProperty("144p") q144p,
    @JsonProperty("240p") q240p,
    @JsonProperty("360p") q360p,
    @JsonProperty("480p") q480p,
    @JsonProperty("720p") q720p,
    @JsonProperty("720p60") q720p60,
    @JsonProperty("1080p") q1080p,
    @JsonProperty("1080p60") q1080p60,
    @JsonProperty("1440p") q1440p,
    @JsonProperty("1440p60") q1440p60,
    @JsonProperty("2160p") q2160p,
    @JsonProperty("2160p60") q2160p60;

    @Override
    public String toString() {
        return name().substring(1);
    }

}
