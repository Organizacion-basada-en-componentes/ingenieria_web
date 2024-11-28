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

import com.organizacion.componentes.back.model.User;
import com.organizacion.componentes.back.model.UserService;

@RestController
public class UserController {

    
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }

    @PostMapping(value = "/user",     consumes = {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<?> saveUser(@RequestBody User user) {
        try{
            userService.addUser(user);
            return ResponseEntity.ok().body("Un nuevo usuario se ha anyadido");
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().body("El usuario ya existe");
        }
	}

    @PostMapping(value = "/user",   consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE} )
	public ResponseEntity<?> saveHTML  (User user) {
        try{
            userService.addUser(user);
            return ResponseEntity.ok().body("Un nuevo usuario se ha anyadido");
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().body("El usuario ya existe");
        }
	}

    @PutMapping("/user")
    public ResponseEntity<?> updateUser(User user) {
        try{
            userService.updateUser(user);
            return ResponseEntity.ok().body("El usuario se ha actualizado");
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().body("Error al actualizar la cuenta");
        }
    }

    @DeleteMapping("/user")
    public ResponseEntity<?> deleteUser(@RequestBody User user){
        try{
            userService.removeUser(user);
            return ResponseEntity.ok().body("El usuario se ha eliminado");
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().body("Error al eliminar usuario");
        }
    }

}

