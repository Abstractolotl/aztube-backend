package de.united.aztube.backend.database;

import de.united.aztube.backend.model.database.PendingLink;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface StatusCodeRepository extends CrudRepository<PendingLink, Integer> {

    PendingLink findByCode(UUID code);
    PendingLink findByDeviceToken(UUID deviceToken);

    void deleteAllByTimestampLessThan(long timestamp);

}
