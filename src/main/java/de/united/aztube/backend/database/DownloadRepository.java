package de.united.aztube.backend.database;

import de.united.aztube.backend.Model.DownloadRequest;
import org.springframework.data.repository.CrudRepository;

public interface DownloadRepository extends CrudRepository<Download , Integer> {

    public DownloadRequest[] findByDeviceToken(String deviceToken);
}
