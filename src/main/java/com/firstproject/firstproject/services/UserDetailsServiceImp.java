package com.firstproject.firstproject.services;

import java.util.Optional;

import com.firstproject.firstproject.Security.UserSS;
import com.firstproject.firstproject.domain.Usuario;
import com.firstproject.firstproject.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService{


    @Autowired
    private UsuarioRepository repo;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Optional<Usuario> usuario = repo.findByLogin(username);

        if(usuario == null){
            System.out.println("Caiu aqui na exception!");
            throw new UsernameNotFoundException(username);
            
        }
        
        return new UserSS(usuario.get().getId(), usuario.get().getLogin(), usuario.get().getSenha(),usuario.get().getRoles());
    }
    
}
