package de.united.aztube.backend;

import de.united.aztube.backend.database.DownloadRepository;
import de.united.aztube.backend.database.LinkRepository;
import de.united.aztube.backend.database.StatusCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@EnableScheduling
public class MonitoringResource {

    private final StatusCodeRepository pendingLinkRepository;
    private final LinkRepository linkRepository;
    private final DownloadRepository downloadRepository;

    @GetMapping("/info")
    public String info() {
        long countPendingLink = pendingLinkRepository.count();
        long countDeviceLinks = linkRepository.count();
        long countDownlods = downloadRepository.count();

        return String.format("""
                Device Links: %s
                Pending Links: %s
                Pending Downloads: %s
                """, countDeviceLinks, countPendingLink, countDownlods);
    }


    @Scheduled(fixedDelay = 1000 * 60 * 60) //Every hour
    public void cleanStatusCodes() {
        pendingLinkRepository.deleteAllByTimestampLessThan(System.currentTimeMillis() - 1000L * 30);
        linkRepository.deleteAllByLastUsedLessThan(System.currentTimeMillis() - 1000L * 60 * 60 * 24 * 30);
    }

}
