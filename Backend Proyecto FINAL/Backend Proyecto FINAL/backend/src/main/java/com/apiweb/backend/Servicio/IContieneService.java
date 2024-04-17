package com.apiweb.backend.Servicio;

import java.util.List;

import com.apiweb.backend.Modelo.ContieneModel;

public interface IContieneService {
    String guardarContiene(ContieneModel contiene);
    ContieneModel buscarContienePorId(Long id);
    void actualizarContiene(Long id, ContieneModel contiene);
    void eliminarContiene(Long id);
    List<ContieneModel> listarContenciones();
}
