package com.apiweb.backend.Servicio;

import java.util.List;

import com.apiweb.backend.Modelo.CompraModel;

public interface ICompraService {
    String guardarCompra(CompraModel compra);
    CompraModel buscarCompraPorId(Integer idCompra);
    String actualizarCompra(CompraModel compra); 
    void eliminarCompra(Integer idCompra);       
    List<CompraModel> listarTodasLasCompras();
}
