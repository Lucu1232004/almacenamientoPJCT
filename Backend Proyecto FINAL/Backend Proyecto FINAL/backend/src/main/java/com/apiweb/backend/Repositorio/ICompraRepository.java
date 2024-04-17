package com.apiweb.backend.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apiweb.backend.Modelo.CompraModel;

public interface ICompraRepository extends JpaRepository<CompraModel,Integer>{
    
}
