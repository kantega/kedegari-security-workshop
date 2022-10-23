package no.kantega.kedegari_security_workshop.service;

import no.kantega.kedegari_security_workshop.dao.SecretRepository;
import no.kantega.kedegari_security_workshop.dto.SecretDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Service
public class SecretService {

    @Autowired
    private SecretRepository secretRepository;

    @Autowired
    private DataSource dataSource;

    public List<SecretDto> getSecrets(String category) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from SECRET where category = ?");
        statement.setString(1, category);

        ResultSet resultSet = statement.executeQuery();
        List<SecretDto> secrets = new ArrayList();
        while (resultSet.next()) {
            secrets.add(
                    new SecretDto(
                            resultSet.getString(resultSet.findColumn("secret"))
                    )
            );
        }
        return secrets;
    }
}
