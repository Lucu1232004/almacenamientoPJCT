package com.apiweb.backend.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiweb.backend.Excepcion.RecursoNoEncontradoException;
import com.apiweb.backend.Modelo.UsuarioModel;
import com.apiweb.backend.Repositorio.IUsuarioRepository;
import com.apiweb.backend.Servicio.IUsuarioService;

@RestController
@RequestMapping ("/apiweb/v2/usuarios")
public class UsuarioController {
    @Autowired
    IUsuarioService usuarioService;
    @Autowired
    IUsuarioRepository usuarioRepository;
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarUsuarioPorID(@PathVariable int id){
        try {
            UsuarioModel usuario = usuarioService.buscarUsuarioPorId(id);
            return ResponseEntity.ok(usuario);
        } catch (RecursoNoEncontradoException e) {
            String mensajeError = e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }
    @GetMapping("/")
    public ResponseEntity<List<UsuarioModel>> listarUsuarios(){
        List<UsuarioModel> usuarios = usuarioService.listarUsuarios();
        return new ResponseEntity<List<UsuarioModel>>(usuarios,HttpStatus.OK);
    }
}
