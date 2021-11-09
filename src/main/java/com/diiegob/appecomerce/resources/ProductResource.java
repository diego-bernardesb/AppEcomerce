package com.diiegob.appecomerce.resources;

import com.diiegob.appecomerce.domain.Category;
import com.diiegob.appecomerce.domain.Product;
import com.diiegob.appecomerce.dto.CategoryDTO;
import com.diiegob.appecomerce.dto.ProductDTO;
import com.diiegob.appecomerce.resources.utils.URL;
import com.diiegob.appecomerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

    @Autowired
    private ProductService service;


    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> find(@PathVariable Integer id) {

        Product obj = service.find(id);
        return ResponseEntity.ok().body(obj);

    }

//    @GetMapping
//    public ResponseEntity<List<ProductDTO>> findAll() {
//        List<Product> list = service.findAll();
//        List<ProductDTO> listDto = list.stream().map(obj -> new ProductDTO(obj)).collect(Collectors.toList());
//        return ResponseEntity.ok().body(listDto);
//    }


    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findPage(
            @RequestParam(value = "nome", defaultValue = "") String nome,
            @RequestParam(value = "categories", defaultValue = "") String categories,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {
        String nomeDecoded = URL.decodeParam(nome);
        List<Integer> ids = URL.decodeIntList(categories);
        Page<Product> list = service.search(nomeDecoded, ids, page, linesPerPage, direction, orderBy);
        Page<ProductDTO> listDto = list.map(obj -> new ProductDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }
}
