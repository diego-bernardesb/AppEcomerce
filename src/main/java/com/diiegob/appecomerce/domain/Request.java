package com.diiegob.appecomerce.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Request implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date instante;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
    private Payment pagamento;

    @ManyToOne //relação bi-direcional
    @JoinColumn(name = "cliente_pedido")
    private Client cliente;

    @ManyToOne //relação direcional
    @JoinColumn(name = "endereco_entrega_id")
    private Address enderecoDeEntrega;

    @OneToMany(mappedBy = "id.pedido")
    private Set<ItemOrder> itens = new HashSet<>();

    public Request() {
    }

    public Request(Integer id, Date instante, Client cliente, Address enderecoDeEntrega) {
        this.id = id;
        this.instante = instante;
        this.cliente = cliente;
        this.enderecoDeEntrega = enderecoDeEntrega;
    }

    public List<Request> getPedidos(){
        List<Request> lista = new ArrayList<>();
        for (ItemOrder x: itens ) {
            lista.add(x.getPedido());
        }
        return lista;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInstante() {
        return instante;
    }

    public void setInstante(Date instante) {
        this.instante = instante;
    }

    public Payment getPagamento() {
        return pagamento;
    }

    public void setPagamento(Payment pagamento) {
        this.pagamento = pagamento;
    }

    public Client getCliente() {
        return cliente;
    }

    public void setCliente(Client cliente) {
        this.cliente = cliente;
    }

    public Address getEnderecoDeEntrega() {
        return enderecoDeEntrega;
    }

    public void setEnderecoDeEntrega(Address enderecoDeEntrega) {
        this.enderecoDeEntrega = enderecoDeEntrega;
    }

    public Set<ItemOrder> getItens() {
        return itens;
    }

    public void setItens(Set<ItemOrder> itens) {
        this.itens = itens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Request)) return false;
        Request request = (Request) o;
        return getId().equals(request.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
