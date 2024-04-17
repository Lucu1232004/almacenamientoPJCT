package com.apiweb.backend.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apiweb.backend.Modelo.EnvioModel;

public interface IEnvioRepository extends JpaRepository<EnvioModel,Integer>{
    
}
