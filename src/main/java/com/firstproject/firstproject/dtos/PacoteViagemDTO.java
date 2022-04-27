package com.firstproject.firstproject.dtos;

public class PacoteViagemDTO {
    
    private String nome;
    private Integer dias;
    private Integer viagem_id;
    
    public PacoteViagemDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public Integer getViagem_id() {
        return viagem_id;
    }

    public void setViagem_id(Integer viagem_id) {
        this.viagem_id = viagem_id;
    }

    

}
