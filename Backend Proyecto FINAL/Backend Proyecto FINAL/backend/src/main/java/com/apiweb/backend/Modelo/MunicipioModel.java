package com.apiweb.backend.Modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table  (name="municipio")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class MunicipioModel {
    @Id
    private Integer codigoPostal;
    private String nombre;
}
