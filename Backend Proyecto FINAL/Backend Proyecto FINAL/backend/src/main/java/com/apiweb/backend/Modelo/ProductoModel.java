package com.apiweb.backend.Modelo;

import java.sql.Blob;

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
@Table  (name="producto")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductoModel {
    @Id
    private Long idProducto;
    private Double precio;
    private Blob imagen;
    @Column(name="tipo")
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    private Integer cantidad;
    @Column(name="seccion")
    @Enumerated(EnumType.STRING)
    private Seccion seccion;
    @Column(name="atuendos")
    @Enumerated(EnumType.STRING)
    private Atuendos atuendos;
    private String tallas;

}
