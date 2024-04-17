package com.apiweb.backend.Modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table  (name="usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor


public class UsuarioModel {
    @Id
    private Integer idUsuario;
    private String nombre;
    private String correo;
    private Integer telefono;
    @Column(name= "Genero")
    @Enumerated(EnumType.STRING)
    private Genero genero;
    private Integer edad;
}
