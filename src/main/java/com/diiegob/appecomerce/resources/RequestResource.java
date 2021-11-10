package com.diiegob.appecomerce.resources;

import com.diiegob.appecomerce.domain.Request;
import com.diiegob.appecomerce.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

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

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody Request obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
