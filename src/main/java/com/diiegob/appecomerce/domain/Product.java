package com.diiegob.appecomerce.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Double preco;

    @JsonBackReference //referencia que os objetos já foram buscado no outro lado da tabela
    @ManyToMany
    @JoinTable(name = "PRODUCT_CATEGORY",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories = new ArrayList<>();

    @JsonIgnore //ignorar a serialização de itens de pedido atravez do produto
    @OneToMany(mappedBy = "id.produto")
    private Set<ItemOrder> itens = new HashSet<>();

    public Product() {
    }

    public Product(Integer id, String nome, Double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    //ignorar listar os produtos associados ao pedido
    @JsonIgnore
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
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
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id) && nome.equals(product.nome) && preco.equals(product.preco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, preco);
    }
}
