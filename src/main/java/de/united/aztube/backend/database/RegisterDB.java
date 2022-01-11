package de.united.aztube.backend.database;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class RegisterDB implements CommandLineRunner {

    @Autowired
    private JdbcTemplate template;

    @Override
    public void run(String... args) throws Exception {
        String sql = "INSERT INTO test01db (browserToken, deviceToken) VALUES (?,?)";
        template.update(sql, "testBrowserToken3", "testDeviceToken3");
    }

    public String checkStatus(String code)  {
        String test = String.valueOf(template.queryForRowSet("SELECT status FROM registerDB WHERE code EQUALS"+ code));
        System.out.println(test);
        return test;
    }
}
