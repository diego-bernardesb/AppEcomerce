package com.diiegob.appecomerce.domain;

import com.diiegob.appecomerce.domain.enuns.StatusPayment;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Entity;

@Entity
@JsonTypeName("cardPayment")
public class CardPayment extends Payment{ //sub-classe de payment
    private static final long serialVersionUID = 1L;

    private Integer numParcelas;

    public CardPayment() {
    }

    public CardPayment(Integer id, StatusPayment estado, Request pedido, Integer numParcelas) {
        super(id, estado, pedido);
        this.numParcelas = numParcelas;
    }

    public Integer getNumParcelas() {
        return numParcelas;
    }

    public void setNumParcelas(Integer numParcelas) {
        this.numParcelas = numParcelas;
    }


}
