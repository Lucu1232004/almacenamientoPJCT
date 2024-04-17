package com.apiweb.backend.Servicio;

import java.util.List;

import com.apiweb.backend.Modelo.ProductoModel;

public interface IProductoService {
    ProductoModel buscarProductoPorId(Long idProducto);
    void actualizarProducto(Long idProducto, ProductoModel producto);
    List<ProductoModel> listarProductos();
}
