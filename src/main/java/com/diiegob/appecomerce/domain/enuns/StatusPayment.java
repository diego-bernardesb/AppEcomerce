package com.diiegob.appecomerce.domain.enuns;

public enum StatusPayment {

    PENDENTE(1, "Pagamento pendente"),
    QUITADO(2, "Pagamento quitado"),
    CANCELADO(3, "Pagamento cancelado");

    private int codigo;
    private String msg;

    StatusPayment(int codigo, String msg) {
        this.codigo = codigo;
        this.msg = msg;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getMsg() {
        return msg;
    }

    public static StatusPayment toEnun(Integer codigo){

        if (codigo == null) {
            return null;
        }

        for (StatusPayment x : StatusPayment.values()) {
            if(codigo.equals(x.getCodigo())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: "+ codigo);
    }
}
