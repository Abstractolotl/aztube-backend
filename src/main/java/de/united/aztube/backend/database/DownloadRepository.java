package de.united.aztube.backend.database;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DownloadRepository extends CrudRepository<Download , Integer> {

    List<Download> findAllByDeviceToken(String deviceToken);

    @Override
    List<Download> findAll();
}
