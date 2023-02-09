package de.united.aztube.backend.database.repository;

import de.united.aztube.backend.database.entity.Token;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TokenRepository extends CrudRepository<Token, Integer> {

    Token findByCode(String code);
    Token findByDeviceToken(String deviceToken);

    @Override
    List<Token> findAll();

}
