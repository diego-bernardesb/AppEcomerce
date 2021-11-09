package com.diiegob.appecomerce.services;

import com.diiegob.appecomerce.domain.Category;
import com.diiegob.appecomerce.domain.Product;
import com.diiegob.appecomerce.repositories.CategoryRepository;
import com.diiegob.appecomerce.repositories.ProductRepository;
import com.diiegob.appecomerce.services.exceptions.ObjNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;
    @Autowired
    CategoryRepository categoryRepository;

//    public List<Product> findAll(){
//        return repo.findAll();
//    }

    public Product find(Integer id){
        Optional<Product> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjNotFoundException(
                "Objeto não encontrado! ID: " + id + " , Tipo: "+ Product.class.getName()));
    }

    public Page<Product> search(String nome, List<Integer> ids,Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(orderBy), direction);
        List<Category> categories = categoryRepository.findAllById(ids);
        return repo.findDistinctByNomeContainingAndCategoriesIn(nome, categories, pageRequest);
    }


}
