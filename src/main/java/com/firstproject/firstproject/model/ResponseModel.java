package com.firstproject.firstproject.model;

import java.util.List;

public class ResponseModel<T> {

    private String mensagem;
    private Integer status;
    private List<T> data;
    
    public ResponseModel(String mensagem, Integer status, List<T> data) {
        this.mensagem = mensagem;
        this.status = status;
        this.data = data;
    }

    public ResponseModel(){
        
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    
}
