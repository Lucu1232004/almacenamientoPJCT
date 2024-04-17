package com.apiweb.backend.Servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiweb.backend.Excepcion.RecursoNoEncontradoException;
import com.apiweb.backend.Modelo.UsuarioModel;
import com.apiweb.backend.Repositorio.IUsuarioRepository;

@Service
public class UsuarioServiceImp implements IUsuarioService{
    @Autowired
    IUsuarioRepository usuarioRepository;
    @Override
    public UsuarioModel buscarUsuarioPorId(Integer idUsuario){
        Optional<UsuarioModel> usuarioEncontrado = usuarioRepository.findById(idUsuario);
        return usuarioEncontrado.
        orElseThrow(()->new RecursoNoEncontradoException("Usuario no Encontrado con el Id "+idUsuario));
    }
    @Override
    public List<UsuarioModel> listarUsuarios() {
        return usuarioRepository.findAll();
    }
}