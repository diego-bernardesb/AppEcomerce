package com.diiegob.appecomerce.domain;

import com.diiegob.appecomerce.domain.enuns.StatusPayment;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) //definir se cada sub-classe será integrada em uma tabela(.SINGLE) ou tabelas independentes(.JOINED)
public abstract class Payment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    private Integer estado;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "pedido_id")
    @MapsId//usar o msm id do pedido para o pagamento
    private Request pedido;

    public Payment() {
    }

    public Payment(Integer id, StatusPayment estado, Request pedido) { //StatusPaymente recebe ENUM e converte em Integer
        this.id = id;
        this.estado = estado.getCodigo();
        this.pedido = pedido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StatusPayment getEstado() {
        return StatusPayment.toEnun(estado);
    }

    public void setEstado(StatusPayment estado) {
        this.estado = estado.getCodigo();
    }

    public Request getPedido() {
        return pedido;
    }

    public void setPedido(Request pedido) {
        this.pedido = pedido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment payment = (Payment) o;
        return getId().equals(payment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
