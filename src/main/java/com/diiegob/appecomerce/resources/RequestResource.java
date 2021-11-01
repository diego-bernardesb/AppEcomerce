package com.diiegob.appecomerce.resources;

import com.diiegob.appecomerce.domain.Request;
import com.diiegob.appecomerce.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/request")
public class RequestResource {

    @Autowired
    private RequestService service;


    @GetMapping(value = "/{id}")
    public ResponseEntity<Request> find(@PathVariable Integer id) {

        Request obj = service.find(id);
        return ResponseEntity.ok().body(obj);


    }
}
