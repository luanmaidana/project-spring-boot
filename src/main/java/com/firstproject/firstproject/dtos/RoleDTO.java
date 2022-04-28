package com.firstproject.firstproject.dtos;

public class RoleDTO {
    
    private String nome;

    public RoleDTO(String nome) {
        this.nome = nome;
    }

    public RoleDTO( ) {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
}
