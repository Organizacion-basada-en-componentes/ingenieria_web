package com.organizacion.componentes.back.service;

public class UserAlreadyExistsException extends Exception {
    // Constructor que recibe un mensaje de error
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
