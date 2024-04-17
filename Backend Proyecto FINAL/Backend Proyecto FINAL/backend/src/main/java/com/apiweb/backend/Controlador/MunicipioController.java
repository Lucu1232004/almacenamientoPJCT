package com.apiweb.backend.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiweb.backend.Excepcion.RecursoNoEncontradoException;
import com.apiweb.backend.Modelo.MunicipioModel;
import com.apiweb.backend.Repositorio.IMunicipioRepository;
import com.apiweb.backend.Servicio.IMunicipioService;

@RestController
@RequestMapping ("/apiweb/v2/municipios")
public class MunicipioController {
    @Autowired
    IMunicipioService municipioService;
    @Autowired
    IMunicipioRepository municipioRepository;
    @GetMapping("/{codigoPostal}")
    public ResponseEntity<?> buscarProductoPorId(@PathVariable Integer codigoPostal){
        try {
            MunicipioModel producto = municipioService.buscarMunicipioPorCodigo(codigoPostal);
            return ResponseEntity.ok(producto);
        } catch (RecursoNoEncontradoException e){
            String mensajeError = e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }
    @GetMapping("/")
    public ResponseEntity<?> buscarTodosLosProductos(){
        return ResponseEntity.ok(municipioRepository.findAll());
    }
}
