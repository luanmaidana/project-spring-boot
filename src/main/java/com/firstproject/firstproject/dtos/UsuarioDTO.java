package com.firstproject.firstproject.dtos;

import lombok.Data;

@Data
public class UsuarioDTO {
    
    private String nome;
    private String cpf;
    private String login;
    private String senha;
    private Integer role_id;
    
    public String getLogin() {
        return login;
    }
    public Integer getRole_id() {
        return role_id;
    }
    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
