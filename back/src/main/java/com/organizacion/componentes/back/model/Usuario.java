package com.organizacion.componentes.back.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor

@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    public long getId() {
        return id;
    }
    public Usuario(long id, String username, String password, Role role, Paciente paciente, Medico medico, String email) {
            this.id = id;
            this.username = username;
            this.password = password;
            this.role = role;
            this.paciente = paciente;
            this.medico = medico;
            this.email = email;
        }
    public Usuario() {
        super();
    }
    public void setId(long id) {
        this.id = id;
    }
    @Column(unique = true, nullable = false)
    private String username;
    @Override
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Column(nullable = true)
    private String password;
    @Override
    public String getPassword() {
        return password;
    }

    public enum Role {
        MEDICO,
        PACIENTE
    }
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    public void setPassword(String passsword) {
        this.password = passsword;
    }

   
    @OneToOne(mappedBy = "usuario") // Indica que 'usuario' en 'Medico' es el lado propietario
    private Medico medico;

    @OneToOne(mappedBy = "usuario") // Indica que 'usuario' en 'Paciente' es el lado propietario
    private Paciente paciente;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    @Column(unique = true, nullable = true)
    private String email;
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

   
  

}

