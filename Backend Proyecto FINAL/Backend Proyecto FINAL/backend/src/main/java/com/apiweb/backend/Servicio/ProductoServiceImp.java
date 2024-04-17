package com.apiweb.backend.Servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiweb.backend.Excepcion.RecursoNoEncontradoException;
import com.apiweb.backend.Modelo.ProductoModel;
import com.apiweb.backend.Repositorio.IContieneRepository;
import com.apiweb.backend.Repositorio.IProductoRepository;

@Service
public class ProductoServiceImp implements IProductoService {
    @Autowired
    IProductoRepository productoRepository;
    @Autowired
    IContieneRepository contieneRepository;
    @Override
    public ProductoModel buscarProductoPorId(Long idProducto){
        Optional<ProductoModel> productoEncontrado = productoRepository.findById(idProducto);
        return productoEncontrado.
        orElseThrow(()->new RecursoNoEncontradoException("Producto no encontrado con el Id "+idProducto));
    }
    @Override
    public void actualizarProducto(Long idProducto, ProductoModel producto) {
        producto.setIdProducto(idProducto); // Asegúrate de establecer el ID correcto en el objeto antes de guardar
        productoRepository.save(producto);
    }
    @Override
    public List<ProductoModel> listarProductos() {
        return productoRepository.findAll();
    }
}
