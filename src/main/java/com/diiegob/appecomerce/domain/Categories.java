package com.diiegob.appecomerce.domain;

import java.io.Serializable;
import java.util.Objects;

public class Categories implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;


    public Categories() {
    }

    public Categories(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categories that = (Categories) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Categories{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
