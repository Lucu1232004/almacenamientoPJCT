package com.apiweb.backend.Servicio;

import java.util.List;

import com.apiweb.backend.Modelo.UsuarioModel;

public interface IUsuarioService {
    UsuarioModel buscarUsuarioPorId(Integer idUsuario);
    List<UsuarioModel> listarUsuarios();
}
