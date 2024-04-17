package com.apiweb.backend.Servicio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiweb.backend.Excepcion.RecursoNoEncontradoException;
import com.apiweb.backend.Modelo.MunicipioModel;
import com.apiweb.backend.Repositorio.IMunicipioRepository;

@Service
public class MunicipioServiceImp implements IMunicipioService{
    @Autowired
    IMunicipioRepository municipioRepository;
    @Override
    public String guardarMunicipio(MunicipioModel municipio) {
        municipioRepository.save(municipio);
        return "El municipio con el codigo "+municipio.getCodigoPostal()+ " fue creado con exito";
    }
    @Override
    public MunicipioModel buscarMunicipioPorCodigo(Integer codigoPostal){
        Optional<MunicipioModel> municipioEncontrado = municipioRepository.findById(codigoPostal);
        return municipioEncontrado.
        orElseThrow(()->new RecursoNoEncontradoException("Municipio no encontrado con el Codigo "+codigoPostal));
    }
}
