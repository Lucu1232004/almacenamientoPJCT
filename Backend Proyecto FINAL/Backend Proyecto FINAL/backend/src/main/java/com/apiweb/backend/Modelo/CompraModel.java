package com.apiweb.backend.Modelo;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="compra")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CompraModel {
    @Id
    private Integer idCompra;
    @ManyToOne
    @JoinColumn(name="idUsuario")
    private UsuarioModel usuarioId;
    private Date fechaCompra;
    private Double costoTotal;

}
