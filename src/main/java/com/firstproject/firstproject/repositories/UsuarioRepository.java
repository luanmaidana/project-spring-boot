package com.firstproject.firstproject.repositories;

import java.util.Optional;

import com.firstproject.firstproject.domain.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    
    public Optional<Usuario> findByLogin(String login);
}
