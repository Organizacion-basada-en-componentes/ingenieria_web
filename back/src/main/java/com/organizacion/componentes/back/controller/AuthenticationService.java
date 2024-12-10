package com.organizacion.componentes.back.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.organizacion.componentes.back.config.JwtService;
import com.organizacion.componentes.back.model.Medico;
import com.organizacion.componentes.back.model.Paciente;
import com.organizacion.componentes.back.model.Usuario;
import com.organizacion.componentes.back.model.Usuario.Role;
import com.organizacion.componentes.back.repository.RepositoryMedico;
import com.organizacion.componentes.back.repository.RepositoryPaciente;
import com.organizacion.componentes.back.repository.RepositoryUsuario;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final RepositoryUsuario repository;
    private final RepositoryMedico repositoryMedico;
    private final RepositoryPaciente repositoryPaciente;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = Usuario.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(request.getRole())
            .email(request.getEmail())
            .build();
        repository.save(user);

            if(request.getRole().equals(Role.PACIENTE)){
                var paciente = Paciente.builder()
                    .usuario(user)
                    .dni(request.getDni())
                    .nombre(request.getNombre())
                    .fechaNacimiento(request.getFechaNacimiento())
                    .build();
                repositoryPaciente.save(paciente);
            } else{
                var medico = Medico.builder()
                    .usuario(user)
                    .dni(request.getDni())
                    .nombre(request.getNombre())
                    .especialidad(request.getEspecialidad())
                    .build();
                repositoryMedico.save(medico);
            }
            
        var token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
            .token(token)
            .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
            )
        );
        var user = repository.findByUsername(request.getUsername())
            .orElseThrow(() -> new RuntimeException("User not found"));
        var token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
            .token(token)
            .build();
    }
    
}
