package com.organizacion.componentes.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.organizacion.componentes.back.controller.Responses.AuthenticationResponse;
import com.organizacion.componentes.back.controller.requests.AuthenticationRequest;
import com.organizacion.componentes.back.controller.requests.RegisterRequest;

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
