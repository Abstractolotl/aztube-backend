package de.united.aztube.backend.database.repository;

import de.united.aztube.backend.database.entity.Download;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DownloadRepository extends CrudRepository<Download, Integer> {

    List<Download> findAllByDeviceToken(String deviceToken);

    @Override
    List<Download> findAll();
}
