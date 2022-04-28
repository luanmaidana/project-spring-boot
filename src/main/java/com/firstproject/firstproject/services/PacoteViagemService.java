package com.firstproject.firstproject.services;

import java.util.List;
import java.util.Optional;

import com.firstproject.firstproject.domain.PacoteViagem;
import com.firstproject.firstproject.domain.Usuario;
import com.firstproject.firstproject.domain.Viagem;
import com.firstproject.firstproject.dtos.PacoteViagemDTO;
import com.firstproject.firstproject.repositories.PacoteViagemRepository;
import com.firstproject.firstproject.repositories.ViagemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exceptions.NotFoundExceptions;

@Service
public class PacoteViagemService {
    
    // Dependencia do repositorio instanciada 
    @Autowired
    private PacoteViagemRepository repo;

    @Autowired
    private ViagemRepository viagemrepo;

    @Autowired
    private UsuarioService usuariorepo;

    // Método para buscar categoria por id
    public Optional<PacoteViagem> buscar(Integer id) throws NotFoundExceptions{
        Optional<PacoteViagem> obj = repo.findById(id);
        
        if(obj.isEmpty()) { throw new NotFoundExceptions(404, "Não encontrado!");}

        return obj;
    }

    public List<PacoteViagem> buscarTodos(){

        List<PacoteViagem> obj = repo.findAll();

        return obj;

    }

    public PacoteViagem addPacoteViagem(PacoteViagem obj){

        obj.setId(null);

        return repo.save(obj);

    }

    public PacoteViagem insertPacoteViagem(PacoteViagemDTO pacoteDTO, String usuariologado){
        
        PacoteViagem pacote = new PacoteViagem();

        Viagem viagem = viagemrepo.findById(pacoteDTO.getViagem_id()).get();

        Usuario usuario = usuariorepo.buscarLogin(usuariologado).get();
        
        pacote.setDias(pacoteDTO.getDias());
        pacote.setNome(pacoteDTO.getNome());
        pacote.setViagem(viagem);
        pacote.setUsuarios(usuario);
        
        return addPacoteViagem(pacote);
    }

    public PacoteViagem update(Integer id, PacoteViagemDTO pacoteViagem){

        PacoteViagem pacote = repo.getById(id);
        Viagem viagem = viagemrepo.getById(pacoteViagem.getViagem_id());

        if(pacote == null){
            return null;
        }

        pacote.setNome(pacoteViagem.getNome());
        pacote.setDias(pacoteViagem.getDias());
        pacote.setViagem(viagem);
        
        return repo.save(pacote);

    }

    public Boolean delete(PacoteViagem pacoteViagem){

        PacoteViagem pacote = repo.getById(pacoteViagem.getId());

        if(pacote == null){
            return false;
        }

        repo.delete(pacote);
        return true;

    }
}
