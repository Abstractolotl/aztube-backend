package de.united.aztube.backend.database;

import de.united.aztube.backend.Model.DownloadRequest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DownloadRepository extends CrudRepository<Download , Integer> {

    List<DownloadRequest> findByDeviceToken(String deviceToken);

    @Override
    List<Download> findAll();
}
