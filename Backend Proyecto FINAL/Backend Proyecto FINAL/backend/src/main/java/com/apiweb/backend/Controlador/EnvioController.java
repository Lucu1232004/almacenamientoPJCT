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
import com.apiweb.backend.Modelo.EnvioModel;
import com.apiweb.backend.Modelo.MunicipioModel;
import com.apiweb.backend.Servicio.ICompraService;
import com.apiweb.backend.Servicio.IEnvioService;
import com.apiweb.backend.Servicio.IMunicipioService;

@RestController
@RequestMapping ("/apiweb/v2/envios")
public class EnvioController {
    @Autowired IEnvioService envioService;
    @Autowired ICompraService compraService;
    @Autowired IMunicipioService municipioService;
    @PostMapping ("/")
    public ResponseEntity<String> crearEnvio(@RequestBody EnvioModel envio){
        try {
            Integer idCompra = envio.getCompraId().getIdCompra();
            CompraModel compra = compraService.buscarCompraPorId(idCompra);    
            Integer codigo = envio.getCodigoPostal().getCodigoPostal();
            MunicipioModel municipio = municipioService.buscarMunicipioPorCodigo(codigo);      
            if(compra!=null && municipio!=null){
                envioService.guardarEnvio(envio);
                return new ResponseEntity<String>(envioService.guardarEnvio(envio),HttpStatus.OK);
            }
            else
                throw new RecursoNoEncontradoException("No se puede realizar el envio, ya que la compra con Id: "
                +idCompra+ " o el Municipio con Codigo: "+codigo+" no fue encontrado");
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarEnvio(@PathVariable Integer id, @RequestBody EnvioModel envioActualizado) {
        try {
            EnvioModel envioExistente = envioService.buscarEnvioPorId(id);
            if (envioExistente == null) {
                throw new RecursoNoEncontradoException("Envío con ID: " + id + " no encontrado.");
            }
            // Actualiza los atributos necesarios
            envioExistente.setCompraId(envioActualizado.getCompraId());
            envioExistente.setCodigoPostal(envioActualizado.getCodigoPostal());
            // Otros atributos a actualizar

            String resultado = envioService.actualizarEnvio(envioExistente);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEnvio(@PathVariable Integer id) {
        try {
            EnvioModel envio = envioService.buscarEnvioPorId(id);
            if (envio == null) {
                throw new RecursoNoEncontradoException("Envío con ID: " + id + " no encontrado.");
            }
            envioService.eliminarEnvio(id);
            return ResponseEntity.ok("Envío eliminado con éxito.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/")
    public ResponseEntity<List<EnvioModel>> listarTodoslosEnvios() {
        List<EnvioModel> envios = envioService.listarTodosLosEnvios();
        if (envios.isEmpty()) {
            return ResponseEntity.noContent().build();
            }
        return ResponseEntity.ok(envios);
        }
}
