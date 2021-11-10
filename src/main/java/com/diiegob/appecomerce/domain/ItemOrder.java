package com.diiegob.appecomerce.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class ItemOrder implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @EmbeddedId
    private ItemOrderPK id = new ItemOrderPK();

    private Double desconto;
    private Integer quantidade;
    private Double preco;

    public ItemOrder() {
    }

    public ItemOrder(Request pedido, Product produto, Double desconto, Integer quantidade, Double preco) {
        id.setPedido(pedido);//recebe o pedido e retorna o seu id
        id.setProduto(produto);//recebe o produto e retorna o seu id
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.preco = preco;
    }


    public double getSubTotal(){
        return (preco - desconto) * quantidade;
    }

    @JsonIgnore
    public Request getPedido() {
        return id.getPedido();
    }

    public void setPedido(Request pedido){
         id.setPedido(pedido);
    }

    public Product getProduto() {
        return id.getProduto();
    }

    public void setProduto(Product produto){
        id.setProduto(produto);
    }

    public ItemOrderPK getId() {
        return id;
    }

    public void setId(ItemOrderPK id) {
        this.id = id;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemOrder)) return false;
        ItemOrder itemOrder = (ItemOrder) o;
        return getId().equals(itemOrder.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
