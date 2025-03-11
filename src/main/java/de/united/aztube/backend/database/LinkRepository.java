package de.united.aztube.backend.database;

import de.united.aztube.backend.model.database.Link;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface LinkRepository extends CrudRepository<Link, Integer> {

    Link findByBrowserToken(UUID browserToken);
    Link findByDeviceToken(UUID deviceToken);
    List<Link> findByBrowserTokenIn(List<UUID> browserTokens);

    void deleteByDeviceToken(UUID deviceToken);
    void deleteAllByLastUsedLessThan(long timestamp);

}
