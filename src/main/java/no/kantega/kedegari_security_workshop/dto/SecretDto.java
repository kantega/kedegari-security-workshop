package no.kantega.kedegari_security_workshop.dto;

public class SecretDto {
    private final String secret;

    public SecretDto(String secret) {
        this.secret = secret;
    }

    public String getSecret() { return secret; }
    public String getCategory() { return "<REDACTED>"; }
}
