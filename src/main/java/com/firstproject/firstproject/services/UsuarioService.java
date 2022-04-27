package com.firstproject.firstproject.services;

import java.util.List;
import java.util.Optional;

import com.firstproject.firstproject.domain.Usuario;
import com.firstproject.firstproject.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exceptions.NotFoundExceptions;

@Service
public class UsuarioService {
    
    // Dependencia do repositorio instanciada 
    @Autowired
    private UsuarioRepository repo;

    // Método para buscar categoria por id
    public Optional<Usuario> buscarPorId(Integer id) throws NotFoundExceptions{
        Optional<Usuario> obj = repo.findById(id);
        
        if(obj.isEmpty()) { throw new NotFoundExceptions(404, "Não encontrado!");}

        return obj;
    }

    public Optional<Usuario> buscarLogin(String login){

        Optional<Usuario> obj = repo.findByLogin(login);

        return obj;
    }
    public List<Usuario> buscarTodos(){

        List<Usuario> obj = repo.findAll();

        return obj;

    }

    public Usuario addUsuario(Usuario obj) {

        repo.save(obj);
        repo.flush();
        return obj;

    }

    public Boolean removeUsuario(Integer id){

        Optional<Usuario> user = repo.findById(id);

        repo.delete(user.get());
        repo.flush();
        return true;

    }

    public Boolean atualizarUsuario(Usuario entity){



        repo.save(entity);
        repo.flush();

        return true;

    }

}
