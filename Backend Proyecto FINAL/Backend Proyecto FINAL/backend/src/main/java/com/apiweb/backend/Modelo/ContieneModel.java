package com.apiweb.backend.Modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table  (name="contiene")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ContieneModel {
    @Id
    private Long idContiene;
    @ManyToOne
    @JoinColumn(name= "idCompra")
    private CompraModel compraId;
    @ManyToOne
    @JoinColumn(name= "idProducto")
    private ProductoModel productoId;
    private Integer cantidad;
}
