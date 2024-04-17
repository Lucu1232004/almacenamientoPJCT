package com.apiweb.backend.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apiweb.backend.Modelo.UsuarioModel;

public interface IUsuarioRepository  extends JpaRepository<UsuarioModel,Integer>{
    
}
