package com.diiegob.appecomerce.resources;

import com.diiegob.appecomerce.domain.Client;
import com.diiegob.appecomerce.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clients")
public class ClienteResource {

    @Autowired
    ClientService service;


    public ResponseEntity<?> find(@PathVariable Integer id){

        Client obj = service.clientById(id);
        return ResponseEntity.ok().body(obj);
    }
}
