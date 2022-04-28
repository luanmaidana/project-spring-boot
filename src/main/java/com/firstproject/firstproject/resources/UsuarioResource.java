package com.firstproject.firstproject.resources;

import java.util.ArrayList;
import java.util.List;

import com.firstproject.firstproject.Config.SecurityConfig;
import com.firstproject.firstproject.domain.Roles;
import com.firstproject.firstproject.domain.Usuario;
import com.firstproject.firstproject.dtos.UsuarioDTO;
import com.firstproject.firstproject.model.ResponseModel;
import com.firstproject.firstproject.services.RoleService;
import com.firstproject.firstproject.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ResponseModel<Usuario>> listarTodos(){

        List<Usuario> usuarios = new ArrayList<>();

        try {
            usuarios = usuarioService.buscarTodos();
            return new ResponseEntity<>(new ResponseModel<>("Sucesso!", 200, usuarios), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseModel<>("Ops, algo deu errado!", 400, usuarios),HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseModel<Usuario>> buscarPorId(@PathVariable Integer id) throws NotFoundExceptions{

        List<Usuario> usuarios = new ArrayList<>();

        Usuario usuario = new Usuario();

        try {
            usuario = usuarioService.buscarPorId(id).get();
            usuarios.add(usuario);
            return new ResponseEntity<>(new ResponseModel<>("Sucesso!", 200, usuarios), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseModel<>("Não foi possível encontrar esse usuário!", 404, usuarios),HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping()
    public ResponseEntity<ResponseModel<Usuario>> post(@RequestBody UsuarioDTO usuario) throws NotFoundExceptions{

        List<Usuario> usuarios = new ArrayList<>();

        Roles roles = new Roles();

        Usuario user =  new Usuario();


        try {

            roles = roleService.buscarPorId(usuario.getRole_id()).get();
            user.setLogin(usuario.getLogin());
            user.setCpf(usuario.getCpf());
            user.setNome(usuario.getNome());
            user.setSenha(usuario.getSenha());
            user.getRoles().add(roles);
            user.setSenha(securityConfig.bCryptPasswordEncoder().encode(usuario.getSenha()));
            usuarioService.addUsuario(user);

            usuarios.add(user);

            return new ResponseEntity<>(new ResponseModel<>("Usuário adicionado com sucesso!", 201, usuarios), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseModel<>("Não foi possível adicionar esse usuário!", 400, usuarios),HttpStatus.BAD_REQUEST);

        }

    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<ResponseModel<Usuario>> deleteUsuario(@PathVariable Integer id) throws NotFoundExceptions{

        List<Usuario> usuarios = new ArrayList<>();

        Usuario user = new Usuario();

        try {
            user = usuarioService.buscarPorId(id).get();
            usuarios.add(user);
            usuarioService.removeUsuario(id);

            return new ResponseEntity<>(new ResponseModel<>("Usuário deletado com sucesso!", 200, usuarios), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseModel<>("Não foi possível deletar esse usuário!", 400, usuarios),HttpStatus.BAD_REQUEST);

        }

    }

}
