package com.apiweb.backend.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apiweb.backend.Modelo.ContieneModel;

public interface IContieneRepository extends JpaRepository<ContieneModel,Long>{
    
}
