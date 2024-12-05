package com.organizacion.componentes.back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.organizacion.componentes.back.model.Usuario;
import com.organizacion.componentes.back.service.UsuarioService;

@RestController
public class UsuarioController {

    
    @Autowired
    private UsuarioService userService;

    @GetMapping("/usuario")
    public List<Usuario> getUsers(){
        return userService.getAllUsuario();
    }

    @GetMapping("/usuario/{id}")
    public Usuario getUser(@PathVariable("id") Long id) {
        return userService.getUsuario(id);
    }

    @PostMapping(value = "/usuario",     consumes = {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<?> saveUser(@RequestBody Usuario user) {
        try{
            userService.addUsuario(user);
            return ResponseEntity.ok().body("Un nuevo usuario se ha anyadido");
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().body("El usuario ya existe");
        }
	}

    @PostMapping(value = "/usuario",   consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE} )
	public ResponseEntity<?> saveHTML  (Usuario user) {
        try{
            userService.addUsuario(user);
            return ResponseEntity.ok().body("Un nuevo usuario se ha anyadido");
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().body("El usuario ya existe");
        }
	}

    @PutMapping("/usuario")
    public ResponseEntity<?> updateUser(Usuario user) {
        try{
            userService.updateUsuario(user);
            return ResponseEntity.ok().body("El usuario se ha actualizado");
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().body("Error al actualizar la cuenta");
        }
    }

    @DeleteMapping("/usuario")
    public ResponseEntity<?> deleteUser(@RequestBody Usuario user){
        try{
            userService.removeUsuario(user);
            return ResponseEntity.ok().body("El usuario se ha eliminado");
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().body("Error al eliminar usuario");
        }
    }

}

