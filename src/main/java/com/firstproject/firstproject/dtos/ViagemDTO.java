package com.firstproject.firstproject.dtos;

public class ViagemDTO {
    
    private Double preco;

    private Integer destino_id;

    private String nomeHotel;


    public void setNomeHotel(String nomeHotel) {
        this.nomeHotel = nomeHotel;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getDestino_id() {
        return destino_id;
    }

    public void setDestino_id(Integer destino_id) {
        this.destino_id = destino_id;
    }

    public Double getPreco() {
        return preco;
    }

    public String getNomeHotel() {
        return nomeHotel;
    }
}
