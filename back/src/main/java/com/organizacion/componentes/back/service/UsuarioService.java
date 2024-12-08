package com.organizacion.componentes.back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organizacion.componentes.back.model.Usuario;
import com.organizacion.componentes.back.repository.RepositoryUsuario;

@Service
public class UsuarioService {

    @Autowired
    RepositoryUsuario repositoryUser;

    public List<Usuario> getAllUsuario() {
        return repositoryUser.findAll();
    }

    public Usuario getUsuario(Long id) {
        return repositoryUser.getReferenceById(id);
    }

    // Usamos la excepción personalizada 'UserAlreadyExistsException'
    public Usuario addUsuario(Usuario u) throws UserAlreadyExistsException {
        // Verificar si el usuario ya existe por email o username
        if (repositoryUser.existsByEmail(u.getEmail())) {
            throw new UserAlreadyExistsException("El usuario con este email ya existe");
        }
        if (repositoryUser.existsByUsername(u.getUsername())) {
            throw new UserAlreadyExistsException("El usuario con este nombre de usuario ya existe");
        }

        // Si el usuario no existe, lo guardamos
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
