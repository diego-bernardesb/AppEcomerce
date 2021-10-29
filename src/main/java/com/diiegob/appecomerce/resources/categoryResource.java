package com.diiegob.appecomerce.resources;

import com.diiegob.appecomerce.domain.Categories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class categoryResource {

    @GetMapping
    public List<Categories> Listar(){

        Categories cat1 = new Categories(1, "informatica");
        Categories cat2 = new Categories(2, "escritorio");

        List<Categories> lista = new ArrayList<>();
        lista.add(cat1);
        lista.add(cat2);

        return lista;
    }
}
