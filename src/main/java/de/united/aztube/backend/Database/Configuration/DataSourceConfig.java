package de.united.aztube.backend.Database.Configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

    @Value("#{environment.MARIA_HOST}")
    private String MARIA_HOST;
    @Value("#{environment.MARIA_USER}")
    private String MARIA_USER;
    @Value("#{environment.MARIA_PW}")
    private String MARIA_PW;

    @Bean
    public DataSource getDataSource() {
        if(MARIA_HOST == null || MARIA_USER == null || MARIA_PW == null) {
            throw new RuntimeException("DB Credentials not found.");
        }
        return DataSourceBuilder.create()
            .driverClassName("org.mariadb.jdbc.Driver")
            .url(MARIA_HOST)
            .username(MARIA_USER)
            .password(MARIA_PW)
            .build();
    }

}
