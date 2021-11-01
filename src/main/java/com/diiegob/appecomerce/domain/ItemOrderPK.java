package com.diiegob.appecomerce.domain;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable//representa uma classe auxiliar
public class ItemOrderPK implements Serializable { //classe que representa a tabela composta entre request e product
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Request pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Product produto;

    public Request getPedido() {
        return pedido;
    }

    public void setPedido(Request pedido) {
        this.pedido = pedido;
    }

    public Product getProduto() {
        return produto;
    }

    public void setProduto(Product produto) {
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemOrderPK)) return false;
        ItemOrderPK that = (ItemOrderPK) o;
        return getPedido().equals(that.getPedido()) && getProduto().equals(that.getProduto());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPedido(), getProduto());
    }
}
