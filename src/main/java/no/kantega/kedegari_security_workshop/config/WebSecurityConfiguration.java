package no.kantega.kedegari_security_workshop.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
public class WebSecurityConfiguration {

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    public String jwkSetUri;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests( authorize -> {
                    authorize.antMatchers("/", "/h2-console/**").permitAll();
                    authorize.antMatchers(HttpMethod.POST, "/secret").hasAuthority("SCOPE_secret:write");
                    authorize.antMatchers("/secret").hasAuthority("SCOPE_secret:read");
                    authorize.anyRequest().authenticated();
                })
                .oauth2ResourceServer(server -> server.jwt(Customizer.withDefaults()));

        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder
                .withJwkSetUri(this.jwkSetUri)
                .build();
    }
}
