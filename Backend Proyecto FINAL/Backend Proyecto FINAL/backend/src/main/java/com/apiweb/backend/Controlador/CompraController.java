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
import com.apiweb.backend.Modelo.UsuarioModel;
import com.apiweb.backend.Servicio.ICompraService;
import com.apiweb.backend.Servicio.IUsuarioService;

@RestController
@RequestMapping ("/apiweb/v2/compras")
public class CompraController {
    @Autowired ICompraService compraService;
    @Autowired IUsuarioService usuarioService;
    @PostMapping("/")
    public ResponseEntity<String> crearCompra(@RequestBody CompraModel compra){
        try {
            Integer idUsuario = compra.getUsuarioId().getIdUsuario();
            UsuarioModel usuario = usuarioService.buscarUsuarioPorId(idUsuario);
            if(usuario!=null){
                compraService.guardarCompra(compra);
                return new ResponseEntity<String>(compraService.guardarCompra(compra),HttpStatus.OK);
            }
            else
                throw new RecursoNoEncontradoException("No se puede realizar la compra, ya que el usuario con Id: "
                +idUsuario+ " no fue encontrado");
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarCompra(@PathVariable Integer idCompra, @RequestBody CompraModel compraActualizada) {
        try {
            CompraModel compraExistente = compraService.buscarCompraPorId(idCompra);
            if (compraExistente == null) {
                throw new RecursoNoEncontradoException("Compra no encontrada con ID: " + idCompra);
            }
            compraActualizada.setIdCompra(idCompra); 
            compraService.actualizarCompra(compraActualizada);
            return ResponseEntity.ok("Compra actualizada exitosamente");
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCompra(@PathVariable Integer id) {
    try {
        CompraModel compra = compraService.buscarCompraPorId(id);
        if (compra == null) {
            throw new RecursoNoEncontradoException("Compra no encontrada con ID: " + id);
        }
        compraService.eliminarCompra(id);
        return ResponseEntity.ok("Compra eliminada exitosamente");
        } catch (RecursoNoEncontradoException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/")
    public ResponseEntity<List<CompraModel>> listarCompras() {
    List<CompraModel> compras = compraService.listarTodasLasCompras();
    if (compras.isEmpty()) {
        return ResponseEntity.noContent().build();
        }
    return ResponseEntity.ok(compras);
    }
}
