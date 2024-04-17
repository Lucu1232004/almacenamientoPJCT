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
@Table  (name="envio")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class EnvioModel {
    @Id
    private Integer idEnvio;
    @ManyToOne
    @JoinColumn(name="idCompra")
    private CompraModel compraId;
    @ManyToOne
    @JoinColumn(name="codigoPostal")
    private MunicipioModel codigoPostal;
    private Date fechaEnvio;
    private String direccion;
}
