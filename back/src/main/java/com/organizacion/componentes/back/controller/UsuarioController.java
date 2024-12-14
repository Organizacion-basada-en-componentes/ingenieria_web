package com.organizacion.componentes.back.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.organizacion.componentes.back.model.Usuario;
import com.organizacion.componentes.back.service.UsuarioService;

@RestController
@RequestMapping("/usuario") // Define una base URL común para los endpoints
public class UsuarioController {

    @Autowired
    private UsuarioService userService;

    // Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> getUsers() {
        try {
            List<Usuario> usuarios = userService.getAllUsuario();
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
        try {
            Optional<Usuario> usuario = userService.getUsuario(id);
            if (usuario.isPresent()) {
                return ResponseEntity.ok(usuario.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario con ID " + id + " no encontrado.");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al obtener el usuario: " + e.getMessage());
        }
    }

    // Crear un nuevo usuario usando JSON
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUser(@RequestBody Usuario user) {
        try {
            Usuario nuevoUsuario = userService.addUsuario(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al crear el usuario: " + e.getMessage());
        }
    }

    // Crear un nuevo usuario desde un formulario
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> saveHTML(Usuario user) {
        try {
            Usuario nuevoUsuario = userService.addUsuario(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al crear el usuario: " + e.getMessage());
        }
    }

    // Actualizar un usuario
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody Usuario user) {
        try {
            userService.updateUsuario(user);
            return ResponseEntity.ok("El usuario se ha actualizado correctamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario con ID " + user.getId() + " no encontrado.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al actualizar el usuario: " + e.getMessage());
        }
    }

    // Eliminar un usuario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            if (userService.getUsuario(id).isPresent()) {
                userService.removeUsuarioID(id);
                return ResponseEntity.ok("Usuario con ID " + id + " eliminado correctamente.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario con ID " + id + " no encontrado.");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al eliminar el usuario: " + e.getMessage());
        }
    }
}
