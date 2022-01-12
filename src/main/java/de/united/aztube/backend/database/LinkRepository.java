package de.united.aztube.backend.database;

import org.springframework.data.repository.CrudRepository;

public interface LinkRepository extends CrudRepository<Link, Integer> {

    Link findByBrowserToken(String browserToken);
    Link findByDeviceToken(String deviceToken);

}
