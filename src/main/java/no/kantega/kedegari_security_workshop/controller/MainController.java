package no.kantega.kedegari_security_workshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MainController {

    @GetMapping("/")
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping("/secret")
    public String secret() {
        return "This is a secret only a handful knows about!";
    }

    @PostMapping("/secret")
    public ResponseEntity<String> createSecret(@RequestBody String secret) {
        return new ResponseEntity<>(secret, HttpStatus.CREATED);
    }
}
