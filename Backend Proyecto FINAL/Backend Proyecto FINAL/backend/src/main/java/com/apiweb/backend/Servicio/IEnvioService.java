package com.apiweb.backend.Servicio;

import java.util.List;

import com.apiweb.backend.Modelo.EnvioModel;

public interface IEnvioService {
    String guardarEnvio(EnvioModel envio);
    String actualizarEnvio(EnvioModel envio);
    void eliminarEnvio(Integer id);
    EnvioModel buscarEnvioPorId(Integer id);
    List<EnvioModel> listarTodosLosEnvios();
}
