package de.united.aztube.backend.database;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StatusCodeRepository extends CrudRepository<StatusDB, Integer> {

    public StatusDB findByCode(String code);

    @Override
    List<StatusDB> findAll();

}
