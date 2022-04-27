package com.firstproject.firstproject.resources;

import java.util.List;
import java.util.Optional;

import com.firstproject.firstproject.Config.SecurityConfig;
import com.firstproject.firstproject.domain.Roles;
import com.firstproject.firstproject.domain.Usuario;
import com.firstproject.firstproject.dtos.UsuarioDTO;
import com.firstproject.firstproject.services.RoleService;
import com.firstproject.firstproject.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    private SecurityConfig securityConfig;

    @Autowired
    private RoleService roleService;

    @GetMapping()
    public ResponseEntity<List<Usuario>> listarTodos(){

        return ResponseEntity.ok(usuarioService.buscarTodos());
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Optional<Usuario>> buscarPorId(@PathVariable Integer id) throws NotFoundExceptions{

        Optional<Usuario> usuario = usuarioService.buscarPorId(id);

        return ResponseEntity.ok(usuario);
    }

    @PostMapping()
    public ResponseEntity<Usuario> post(@RequestBody UsuarioDTO usuario) throws NotFoundExceptions{

        Optional<Roles> roles = roleService.buscarPorId(usuario.getRole_id());

        Usuario user =  new Usuario();
        user.setLogin(usuario.getLogin());
        user.setCpf(usuario.getCpf());
        user.setNome(usuario.getNome());
        user.setSenha(usuario.getSenha());
        user.getRoles().add(roles.get());
        user.setSenha(securityConfig.bCryptPasswordEncoder().encode(usuario.getSenha()));
        usuarioService.addUsuario(user);

        return ResponseEntity.ok(user);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Boolean> deleteUsuario(@PathVariable Integer id) throws NotFoundExceptions{

        Optional<Usuario> user = usuarioService.buscarPorId(id);

        if(user == null){
            return ResponseEntity.badRequest().body(false);
        }

        usuarioService.removeUsuario(id);

        return ResponseEntity.ok(true);

    }

}
