package com.organizacion.componentes.back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.organizacion.componentes.back.model.Usuario;
import com.organizacion.componentes.back.model.Usuario.Role;
import com.organizacion.componentes.back.repository.RepositoryUsuario;

@Service
public class UsuarioService {

    private final RepositoryUsuario repositoryUser;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(RepositoryUsuario repositoryUser, PasswordEncoder passwordEncoder) {
        this.repositoryUser = repositoryUser;
        this.passwordEncoder = passwordEncoder;
    }

    // Crear usuario con contraseña codificada
    public Usuario saveUser(String username, String password, Role role) {
        Usuario user = new Usuario();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); // Codificar contraseña
        user.setRole(role);
        return repositoryUser.save(user);
    }

    // Buscar usuario por username
    public Optional<Usuario> findByUsername(String username) {
        return repositoryUser.findByUsername(username);
    }

    // Obtener todos los usuarios
    public List<Usuario> getAllUsuario() {
        return repositoryUser.findAll();
    }

    // Obtener usuario por ID
    public Optional<Usuario> getUsuario(Long id) {
        return repositoryUser.findById(id);
    }

    // Agregar usuario
    public Usuario addUsuario(Usuario u) {
        return repositoryUser.save(u);
    }

    // Actualizar usuario
    public void updateUsuario(Usuario u) {
        Optional<Usuario> optionalUser = repositoryUser.findById(u.getId());
        if (optionalUser.isPresent()) {
            Usuario user = optionalUser.get();
            user.setPassword(passwordEncoder.encode(u.getPassword())); // Codificar contraseña
            user.setRole(u.getRole());
            user.setEmail(u.getEmail());
            repositoryUser.save(user);
        } else {
            throw new IllegalArgumentException("Usuario con ID " + u.getId() + " no existe.");
        }
    }

    // Eliminar usuario por entidad
    public void removeUsuario(Usuario u) {
        repositoryUser.delete(u);
    }

    // Eliminar usuario por ID con validación
    public void removeUsuarioID(Long id) {
        if (repositoryUser.existsById(id)) {
            repositoryUser.deleteById(id);
        } else {
            throw new IllegalArgumentException("Usuario con ID " + id + " no existe.");
        }
    }
}
