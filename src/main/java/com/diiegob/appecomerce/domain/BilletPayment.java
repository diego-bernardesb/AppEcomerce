package com.diiegob.appecomerce.domain;

import com.diiegob.appecomerce.domain.enuns.StatusPayment;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class BilletPayment extends Payment{ //sub-classe de payment
    private static final long serialVersionUID = 1L;

    @Temporal(TemporalType.DATE) //insere apenas a data no banco de dados
    private Date dataVencimento;
    @Temporal(TemporalType.DATE)
    private Date dataPagamento;

    public BilletPayment() {
    }

    public BilletPayment(Integer id, StatusPayment estado, Request pedido, Date dataVencimento, Date dataPagamento) {
        super(id, estado, pedido);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}
