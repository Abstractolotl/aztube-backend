package de.united.aztube.backend.database;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StatusCodeRepository extends CrudRepository<StatusDB, Integer> {

    StatusDB findByCode(String code);
    StatusDB findByDeviceToken(String deviceToken);

    @Override
    List<StatusDB> findAll();

}
