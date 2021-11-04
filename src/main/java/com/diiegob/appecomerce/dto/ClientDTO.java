package com.diiegob.appecomerce.dto;

import com.diiegob.appecomerce.domain.Client;
import com.diiegob.appecomerce.services.validation.ClientUpdate;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ClientUpdate
public class ClientDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatorio")
    @Length(min = 3, max = 120, message = "Deve ter entre 3 e 120 caracteres")
    private String nome;

    @NotEmpty(message = "Preenchimento obrigatorio")
    @Email(message = "E-mail inválido")
    private String email;

    public ClientDTO() {
    }

    public ClientDTO(Client obj) {
        id = obj.getId();
        nome = obj.getNome();
        email = obj.getEmail();
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
}