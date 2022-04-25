package com.firstproject.firstproject.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class PacoteViagem implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private Double preco;

    private Integer dias;

    @OneToOne
    private Viagem viagem;

    @OneToMany
    @JoinTable(name="pacoteViagem_usuario", 
               joinColumns = @JoinColumn(name = "viagem_id"), 
               inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private List<Usuario> usuarios = new ArrayList<>();
    
    public PacoteViagem(){

    }

    public PacoteViagem(Integer id, Viagem viagem, String nome, Double preco, Integer dias){
        this.id = id;
        this.viagem = viagem;
        this.nome = nome;
        this.preco = preco;
        this.dias = dias;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Viagem getViagem(){
        return viagem;
    }

    public void setNome(Viagem viagem){
        this.viagem = viagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PacoteViagem other = (PacoteViagem) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }



    

}
