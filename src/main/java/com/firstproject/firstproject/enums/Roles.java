package com.firstproject.firstproject.enums;

public enum Roles {
    
    ADMIN(1, "ROLE_ADMIN"),
    CLIENTE(2, "ROLE_CLIENTE");

    private Integer id;
    private String descricao;
    
    private Roles(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static Roles toEnum(Integer id){

        if(id == null){
            return null;
        }

        for(Roles x : Roles.values()){
            if(id.equals(x.getId())){
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + id);
    }

}
