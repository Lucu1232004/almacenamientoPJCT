package com.apiweb.backend.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiweb.backend.Excepcion.RecursoNoEncontradoException;
import com.apiweb.backend.Modelo.ProductoModel;
import com.apiweb.backend.Repositorio.IProductoRepository;
import com.apiweb.backend.Servicio.IProductoService;

@RestController
@RequestMapping ("/apiweb/v2/productos")
public class ProductoController {
    @Autowired
    IProductoService productoService;
    @Autowired
    IProductoRepository productoRepository;
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarProductoPorId(@PathVariable Long id){
        try {
            ProductoModel producto = productoService.buscarProductoPorId(id);
            return ResponseEntity.ok(producto);
        } catch (RecursoNoEncontradoException e){
            String mensajeError = e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }
    @GetMapping("/")
    public ResponseEntity<?> buscarTodosLosProductos(){
        return ResponseEntity.ok(productoRepository.findAll());
    }
}
