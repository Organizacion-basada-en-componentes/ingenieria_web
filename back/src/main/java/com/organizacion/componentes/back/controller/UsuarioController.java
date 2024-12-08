package com.organizacion.componentes.back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.organizacion.componentes.back.model.Usuario;
import com.organizacion.componentes.back.service.UsuarioService;
import com.organizacion.componentes.back.service.UserAlreadyExistsException; // Asegúrate de crear esta excepción personalizada

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService userService;

    @GetMapping
    public List<Usuario> getUsers() {
        return userService.getAllUsuario();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUser(@PathVariable("id") Long id) {
        Usuario user = userService.getUsuario(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveUser(@RequestBody Usuario user) {
        try {
            // Intentar agregar el usuario
            userService.addUsuario(user);
            return ResponseEntity.ok("Un nuevo usuario se ha añadido");
        } catch (UserAlreadyExistsException e) {
            // Si el usuario ya existe, devolver un error 400 con el mensaje específico
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            // Registrar el error con el stack trace para obtener más detalles
            e.printStackTrace();
            // Si ocurre cualquier otro error, devolver un error 500
            return ResponseEntity.status(500).body("Error al guardar el usuario: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody Usuario user) {
        try {
            userService.updateUsuario(user);
            return ResponseEntity.ok("El usuario se ha actualizado");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al actualizar el usuario");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        try {
            Usuario user = userService.getUsuario(id);
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            userService.removeUsuario(user);
            return ResponseEntity.ok("El usuario se ha eliminado");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar el usuario");
        }
    }
}
