package com.diiegob.appecomerce.domain;

import com.diiegob.appecomerce.domain.enuns.TypeClient;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    private String cpfOuCnpj;
    private Integer tipo;


    @OneToMany(mappedBy = "cliente")
    private List<Address> enderecos = new ArrayList<>();

    @ElementCollection //representa uma entidade fraca
    @CollectionTable(name = "telefones") //nome da tabela que irá representa essa entidade fraca
    private Set<String> phones = new HashSet<>();

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Request> pedidos = new ArrayList<>();


    public Client() {
    }

    public Client(Integer id, String nome, String email, String cpfOuCnpj, TypeClient tipo) {//tipo, recebe um enum e salva um integer
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipo = (tipo == null) ? null : tipo.getCodigo();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public TypeClient getTipo() {
        return TypeClient.toEnun(tipo);
    }

    public void setTipo(TypeClient tipo) {
        this.tipo = tipo.getCodigo();
    }

    public List<Address> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Address> enderecos) {
        this.enderecos = enderecos;
    }

    public Set<String> getPhones() {
        return phones;
    }

    public void setPhones(Set<String> phones) {
        this.phones = phones;
    }

    public List<Request> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Request> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return getId().equals(client.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
