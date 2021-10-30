package com.diiegob.appecomerce.domain.enuns;

public enum TypeClient {

    PESSOAFISICA(1, "Pessoa Física"),
    PESSOAJURIDICA(2, "Pessoa Jurídica");

    private int codigo;
    private String descricao;

    private TypeClient(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TypeClient toEnun(Integer codigo){

        if (codigo == null) {
            return null;
        }

        for (TypeClient x : TypeClient.values()) {
            if(codigo.equals(x.getCodigo())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: "+ codigo);
    }
}
