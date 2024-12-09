package com.organizacion.componentes.back.controller;

import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.webauthn.api.AuthenticatorAssertionResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.organizacion.componentes.back.config.JwtService;
import com.organizacion.componentes.back.model.Usuario;
import com.organizacion.componentes.back.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController

@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService service;


    @PostMapping("/auth/register")
    public ResponseEntity<AuthenticationResponse> register(
        @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }



    @PostMapping("/auth/login")
    public ResponseEntity<AuthenticationResponse> login(
        @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

}
