package com.organizacion.componentes.back.controller.requests;

import java.sql.Date;

import com.organizacion.componentes.back.model.Usuario.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private Role role;
    private String email;
    private String dni;
    private String nombre;
    private Date fechaNacimiento;
    private String especialidad;
    
   
}
