package de.united.aztube.backend.database.repository;

import de.united.aztube.backend.database.entity.Link;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LinkRepository extends CrudRepository<Link, Integer> {

    Link findByBrowserToken(String browserToken);
    Link findByDeviceToken(String deviceToken);

    void deleteByDeviceToken(String deviceToken);

    List<Link> findAllByBrowserTokenIn(List<String> browserTokens);

}
