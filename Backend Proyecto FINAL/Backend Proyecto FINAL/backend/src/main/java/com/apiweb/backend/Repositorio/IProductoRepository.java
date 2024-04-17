package com.apiweb.backend.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apiweb.backend.Modelo.ProductoModel;

public interface IProductoRepository extends JpaRepository<ProductoModel,Long>{

    
}
