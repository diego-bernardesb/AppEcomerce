package com.diiegob.appecomerce.services;

import com.diiegob.appecomerce.domain.Client;
import com.diiegob.appecomerce.repositories.ClientRepository;
import com.diiegob.appecomerce.services.exceptions.ObjNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repo;

    public Client clientById(Integer id){
        Optional<Client> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjNotFoundException(
                "Cliente não encontrado! ID: " + id +", Tipo: " + Client.class.getName()));
    }
}
