package de.united.aztube.backend.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QualityService {

    public List<String> getSupportedQualities() {
        List<String> supportedQualities = new ArrayList<>();

        supportedQualities.add("audio");

        supportedQualities.add("144p");
        supportedQualities.add("240p");
        supportedQualities.add("360p");
        supportedQualities.add("480p");
        supportedQualities.add("720p");
        supportedQualities.add("1080p");
        supportedQualities.add("1440p");
        supportedQualities.add("2160p");

        supportedQualities.add("720p60");
        supportedQualities.add("1080p60");
        supportedQualities.add("1440p60");
        supportedQualities.add("2160p60");

        return supportedQualities;
    }

}
