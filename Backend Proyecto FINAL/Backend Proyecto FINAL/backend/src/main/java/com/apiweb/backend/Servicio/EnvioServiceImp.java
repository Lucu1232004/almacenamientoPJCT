package com.apiweb.backend.Servicio;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiweb.backend.Modelo.EnvioModel;
import com.apiweb.backend.Repositorio.IEnvioRepository;

@Service
public class EnvioServiceImp implements IEnvioService{
@Autowired IEnvioRepository envioRepository;

    @Override
    public String guardarEnvio(EnvioModel envio) {
        envioRepository.save(envio);
        return "El envio con Id "+envio.getIdEnvio()+ " de la compra con Id "+envio.getCompraId().getIdCompra()+
        " dirigido al municipio con codigo "+envio.getCodigoPostal().getCodigoPostal()+" fue creado con exito";
    }
    @Override
    public String actualizarEnvio(EnvioModel envio) {
        envioRepository.save(envio);
        return "El envío con Id " + envio.getIdEnvio() + " fue actualizado con éxito.";
    }

    @Override
    public void eliminarEnvio(Integer idEnvio) {
        envioRepository.deleteById(idEnvio);
    }

    @Override
    public EnvioModel buscarEnvioPorId(Integer idEnvio) {
        Optional<EnvioModel> envioOptional = envioRepository.findById(idEnvio);
        return envioOptional.orElse(null);
    }

    @Override
    public List<EnvioModel> listarTodosLosEnvios() {
        return envioRepository.findAll();
    }
}

