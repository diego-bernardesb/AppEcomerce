package com.diiegob.appecomerce.services;

import com.diiegob.appecomerce.domain.Category;
import com.diiegob.appecomerce.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repo;

    public Category findById(Integer id){
        Optional<Category> obj = repo.findById(id);
        return obj.orElse(null);
    }
}
