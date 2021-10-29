package com.diiegob.appecomerce.resources;

import com.diiegob.appecomerce.domain.Category;
import com.diiegob.appecomerce.repositories.CategoryRepository;
import com.diiegob.appecomerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class categoryResource {

    @Autowired
    private CategoryService service;


    @GetMapping(value = "/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {

        Category obj = service.findById(id);
        return ResponseEntity.ok().body(obj);


    }
}
