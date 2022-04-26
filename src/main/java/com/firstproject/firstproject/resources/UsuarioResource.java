package com.firstproject.firstproject.resources;

import java.util.List;
import java.util.Optional;

import com.firstproject.firstproject.domain.Usuario;
import com.firstproject.firstproject.dtos.UsuarioDTO;
import com.firstproject.firstproject.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import exceptions.NotFoundExceptions;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioResource{
    
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping()
    public ResponseEntity<List<Usuario>> listarTodos(){

        return ResponseEntity.ok(usuarioService.buscarTodos());
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Optional<Usuario>> buscarPorId(@PathVariable Integer id) throws NotFoundExceptions{

        Optional<Usuario> usuario = usuarioService.buscarPorId(id);

        return ResponseEntity.ok(usuario);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Usuario> post(@RequestBody Usuario usuario){

        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return ResponseEntity.ok(usuarioService.addUsuario(usuario));
    }

    @PostMapping(value = "/validarsenha")
    public ResponseEntity<Boolean> validarSenha(@RequestBody UsuarioDTO obj){

        Optional<Usuario> usuario = usuarioService.buscarLogin(obj.getLogin());

        if(usuario.isEmpty()) {
            //System.out.println("caiu aqui!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }

         boolean valid = false;

        Usuario user = usuario.get();
         valid = encoder.matches(obj.getSenha(), user.getSenha());

        if(valid == false){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }

        return ResponseEntity.status(HttpStatus.OK).body(valid);
    }

}
