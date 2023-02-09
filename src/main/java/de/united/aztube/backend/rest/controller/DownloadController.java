package de.united.aztube.backend.rest.controller;

import de.united.aztube.backend.database.entity.Download;
import de.united.aztube.backend.database.entity.Link;
import de.united.aztube.backend.database.entity.Token;
import de.united.aztube.backend.database.repository.DownloadRepository;
import de.united.aztube.backend.database.repository.LinkRepository;
import de.united.aztube.backend.database.repository.TokenRepository;
import de.united.aztube.backend.model.request.DownloadRequest;
import de.united.aztube.backend.model.request.StatusRequest;
import de.united.aztube.backend.model.response.DownloadResponse;
import de.united.aztube.backend.model.response.PollResponse;
import de.united.aztube.backend.model.response.StatusResponse;
import de.united.aztube.backend.rest.api.DownloadAPI;
import de.united.aztube.backend.services.QualityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
public class DownloadController implements DownloadAPI {

    private final QualityService qualityService;
    private final DownloadRepository downloadRepository;
    private final LinkRepository linkRepository;
    private final TokenRepository tokenRepository;

    public DownloadController(QualityService qualityService, TokenRepository tokenRepository,
                              LinkRepository linkRepository, DownloadRepository downloadRepository) {
        this.qualityService = qualityService;
        this.tokenRepository = tokenRepository;
        this.linkRepository = linkRepository;
        this.downloadRepository = downloadRepository;
    }

    @Override
    public DownloadResponse download(DownloadRequest request) {
        if(!qualityService.getSupportedQualities().contains(request.getQuality())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quality not supported");
        }

        Link link = linkRepository.findByBrowserToken(request.getBrowserToken());
        if(link == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Link not found");
        }

        Download download = new Download();

        download.setDeviceToken(link.getDeviceToken());
        download.setTitle(request.getTitle());
        download.setQuality(request.getQuality());
        download.setAuthor(request.getAuthor());
        download.setVideoId(request.getVideoId());

        downloadRepository.save(download);
        return DownloadResponse.builder()
                .success(true).error(null)
                .build();
    }

    @Override
    public PollResponse poll(String deviceToken) {
        Link link = linkRepository.findByDeviceToken(deviceToken);
        if(link == null){
            Token token = tokenRepository.findByDeviceToken(deviceToken);
            if(token == null){
                throw new ResponseStatusException(HttpStatus.PROCESSING, "DeviceToken not ready yet");
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "DeviceToken not found");
        }

        List<Download> downloads = downloadRepository.findAllByDeviceToken(deviceToken);
        downloadRepository.deleteAll(downloads);

        if (downloads.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No downloads available");
        }

        return PollResponse.builder()
                .success(true).error(null)
                .downloads(downloads)
                .build();
    }
}
