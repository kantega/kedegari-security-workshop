package no.kantega.kedegari_security_workshop.service;

import no.kantega.kedegari_security_workshop.dto.SecretDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class SecretServiceTest {

    @Autowired
    private SecretService secretService;

    @Test
    public void given_category_only_categorized_secret_is_returned() throws SQLException {
        List<SecretDto> secrets = secretService.getSecrets("LOW");
        assertTrue(secrets.stream().allMatch(secret -> Objects.equals(secret.getCategory(), "LOW")));
    }

    @Test
    public void given_injection_search_is_resilient() throws SQLException {
        List<SecretDto> secrets = secretService.getSecrets("LOW' or 1 = 1;--");
        assertTrue(secrets.stream().allMatch(secret -> Objects.equals(secret.getCategory(), "LOW")));
    }

    @Test
    public void verify_output_secret_does_not_contain_protected_information() throws SQLException {
        List<SecretDto> secrets = secretService.getSecrets("LOW");
    }
}
