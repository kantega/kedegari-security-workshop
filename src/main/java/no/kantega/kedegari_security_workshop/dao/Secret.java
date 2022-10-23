package no.kantega.kedegari_security_workshop.dao;

import javax.persistence.*;


@Entity
public class Secret {

    public Secret() {

    }

    public Secret(String secret, String category) {
        this.secret = secret;
        this.category = category;
    }

    public Secret(Long id, String secret, String category) {
        this.id = id;
        this.secret = secret;
        this.category = category;
    }

    public static class SecretBuilder {
        private String secret;
        private String category;

        public SecretBuilder withSecret(String secret) {
            this.secret = secret;
            return this;
        }

        public SecretBuilder withCategory(String category) {
            this.category = category;
            return this;
        }

        public Secret build() {
            return new Secret(this);
        }
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    private String secret;
    public String getSecret() { return secret; }
    public void setSecret(String secret) { this.secret = secret; }

    private String category;
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Secret(SecretBuilder builder) {
        this.secret = builder.secret;
        this.category = builder.category;
    }

    @Override
    public String toString() {
        return "Secret [id=" + id + ", secret=" + secret + ", category=" + category + "]";
    }
}
