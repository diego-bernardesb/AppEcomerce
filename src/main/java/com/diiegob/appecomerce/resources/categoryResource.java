package com.diiegob.appecomerce.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categories")
public class categoryResource {

    @GetMapping
    public String Listar(){
        return "Rest está funcionando!";
    }
}
