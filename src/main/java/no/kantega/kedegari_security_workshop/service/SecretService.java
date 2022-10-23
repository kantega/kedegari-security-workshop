package no.kantega.kedegari_security_workshop.service;

import no.kantega.kedegari_security_workshop.dao.Secret;
import no.kantega.kedegari_security_workshop.dao.SecretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class SecretService {

    @Autowired
    private SecretRepository secretRepository;

    @Autowired
    private DataSource dataSource;

    public List<Secret> getSecrets(String category) throws SQLException {
        String query = String.format("select * from SECRET where category = '%s'", category);
        ResultSet resultSet = dataSource.getConnection().createStatement().executeQuery(query);
        List<Secret> secrets = new ArrayList();
        while (resultSet.next()) {
            secrets.add(
                    new Secret(
                            resultSet.getLong(resultSet.findColumn("id")),
                            resultSet.getString(resultSet.findColumn("secret")),
                            resultSet.getString(resultSet.findColumn("category"))
                    )
            );
        }
        return secrets;
    }
}
