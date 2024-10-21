package de.united.aztube.backend.database;

import de.united.aztube.backend.model.database.Download;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface DownloadRepository extends CrudRepository<Download, Integer> {

    List<Download> findAllByDeviceToken(UUID deviceToken);

}
