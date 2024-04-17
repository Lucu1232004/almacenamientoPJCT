package com.apiweb.backend.Servicio;

import com.apiweb.backend.Modelo.MunicipioModel;

public interface IMunicipioService {
    String guardarMunicipio(MunicipioModel municipio);
    MunicipioModel buscarMunicipioPorCodigo(Integer codigoPostal);
}
