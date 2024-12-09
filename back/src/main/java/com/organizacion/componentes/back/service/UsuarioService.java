package com.organizacion.componentes.back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.organizacion.componentes.back.model.Usuario;
import com.organizacion.componentes.back.model.Usuario.Role;
import com.organizacion.componentes.back.repository.RepositoryUsuario;

@Service
public class UsuarioService {

    private final PasswordEncoder passwordEncoder;
    RepositoryUsuario repositoryUser;

    public UsuarioService(RepositoryUsuario repositoryUser, PasswordEncoder passwordEncoder) {
        this.repositoryUser = repositoryUser;
        this.passwordEncoder = passwordEncoder;
    }


    public Usuario saveUser(String username, String password, Role role) {
        Usuario user = new Usuario();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));  // Codificar la contraseña
        user.setRole(role);
        return repositoryUser.save(user);
    }

    public Optional<Usuario> findByUsername(String username) {
        return repositoryUser.findByUsername(username);
    }




    public List<Usuario> getAllUsuario() {
        return repositoryUser.findAll();
    }

    public Usuario getUsuario(Long id) {
        return repositoryUser.getReferenceById(id);
    }

    public Usuario addUsuario(Usuario u) {
        return repositoryUser.saveAndFlush(u);
    }

    public void updateUsuario(Usuario u) {
        Usuario user = repositoryUser.getReferenceById(u.getId());
        user.setPassword(u.getPassword());
        user.setRole(u.getRole());
        user.setEmail(u.getEmail());
        repositoryUser.save(user);
    }

    public void removeUsuario(Usuario u) {
        repositoryUser.delete(u);
    }

    public void removeUsuarioID(Long id) {
        repositoryUser.deleteById(id);
    }
}
