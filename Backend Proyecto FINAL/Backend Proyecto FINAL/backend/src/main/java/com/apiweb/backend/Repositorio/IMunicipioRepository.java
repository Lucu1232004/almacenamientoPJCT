package com.apiweb.backend.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apiweb.backend.Modelo.MunicipioModel;

public interface IMunicipioRepository extends JpaRepository<MunicipioModel,Integer>{
    
}
