package com.apiweb.backend.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiweb.backend.Excepcion.RecursoNoEncontradoException;
import com.apiweb.backend.Modelo.CompraModel;
import com.apiweb.backend.Modelo.ContieneModel;
import com.apiweb.backend.Modelo.ProductoModel;
import com.apiweb.backend.Servicio.ICompraService;
import com.apiweb.backend.Servicio.IContieneService;
import com.apiweb.backend.Servicio.IProductoService;

@RestController
@RequestMapping ("/apiweb/v2/contiene")
public class ContieneController {
    @Autowired IContieneService contieneService;
    @Autowired ICompraService compraService;
    @Autowired IProductoService productoService;
    @PostMapping ("/")
    public ResponseEntity<String> crearContencion(@RequestBody ContieneModel contiene){
        try {
            Integer idCompra = contiene.getCompraId().getIdCompra();
            CompraModel compra = compraService.buscarCompraPorId(idCompra);    
            Long idProducto = contiene.getProductoId().getIdProducto();
            ProductoModel producto = productoService.buscarProductoPorId(idProducto);      
            Integer nuevaCantidad = (Integer) (producto.getCantidad() - contiene.getCantidad());
            producto.setCantidad(nuevaCantidad);
            productoService.actualizarProducto(producto.getIdProducto(), producto);
            if(compra!=null && producto!=null){
                contieneService.guardarContiene(contiene);
                return new ResponseEntity<String>(contieneService.guardarContiene(contiene),HttpStatus.OK);
            }
            else
                throw new RecursoNoEncontradoException("No se puede realizar la reseña, ya que la compra con Id: "
                +idCompra+ " o el producto con Id: "+idProducto+" no fue encontrado"); 
            } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
public ResponseEntity<String> actualizarContencion(@PathVariable Long id, @RequestBody ContieneModel contieneActualizado) {
    try {
        ContieneModel contieneExistente = contieneService.buscarContienePorId(id);
        if (contieneExistente != null) {
            contieneService.actualizarContiene(id, contieneActualizado);
            return ResponseEntity.ok("Contención actualizada correctamente");
        } else {
            throw new RecursoNoEncontradoException("La contención con ID " + id + " no fue encontrada");
        }
    } catch (RecursoNoEncontradoException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
    @GetMapping("/{id}")
public ResponseEntity<ContieneModel> buscarContencionPorId(@PathVariable Long id) {
    ContieneModel contiene = contieneService.buscarContienePorId(id);
    if (contiene != null) {
        return ResponseEntity.ok(contiene);
    } else {
        return ResponseEntity.notFound().build();
    }
}
    @DeleteMapping("/{id}")
public ResponseEntity<String> eliminarContencion(@PathVariable Long id) {
    try {
        contieneService.eliminarContiene(id);
        return ResponseEntity.ok("Contención eliminada correctamente");
    } catch (RecursoNoEncontradoException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
    @GetMapping("/")
public ResponseEntity<List<ContieneModel>> listarContenciones() {
    List<ContieneModel> contenciones = contieneService.listarContenciones();
    return ResponseEntity.ok(contenciones);
}

}
