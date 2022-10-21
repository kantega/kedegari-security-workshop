package no.kantega.kedegari_security_workshop.controller;

import no.kantega.kedegari_security_workshop.config.WebSecurityConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = MainController.class)
@Import(WebSecurityConfiguration.class)
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void given_anonymous_request_hello_world_is_granted() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void given_authenticated_request_secret_access_is_forbidden() throws Exception {
        mockMvc.perform(get("/secret"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser
    public void given_authorized_request_secret_access_is_granted() throws Exception {
        mockMvc.perform(get("/secret")
                .with(jwt()
                        .authorities(new SimpleGrantedAuthority("SCOPE_secret:read"))
                ))
                .andExpect(status().isOk());
    }
}
