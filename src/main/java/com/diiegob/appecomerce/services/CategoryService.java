package com.diiegob.appecomerce.services;

import com.diiegob.appecomerce.domain.Category;
import com.diiegob.appecomerce.repositories.CategoryRepository;
import com.diiegob.appecomerce.services.exceptions.DataIntegrityViolationException;
import com.diiegob.appecomerce.services.exceptions.ObjNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repo;

    public Category find(Integer id){
        Optional<Category> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjNotFoundException(
                "Objeto não encontrado! ID: " + id + " , Tipo: "+ Category.class.getName()));
    }

    public Category insert(Category obj){
        obj.setId(null);
        return repo.save(obj);
    }

    public Category update(Category obj){
        find(obj.getId());//reutiliza o codigo get para confirmar o objeto
        return repo.save(obj);
    }

    public void delete(Integer id) {
        find(id);
        //exceção personalisada para não estourar error 5xx
        try {
            repo.deleteById(id);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Não é possivel deletar uma categoria contendo produtos");
        }
    }

}
