package com.diiegob.appecomerce.services;

import com.diiegob.appecomerce.domain.Category;
import com.diiegob.appecomerce.dto.CategoryDTO;
import com.diiegob.appecomerce.repositories.CategoryRepository;
import com.diiegob.appecomerce.services.exceptions.DataIntegrityViolationException;
import com.diiegob.appecomerce.services.exceptions.ObjNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Category> findAll(){
        return repo.findAll();
    }

    public Page<Category> findPage(Integer page, Integer linesPerPage, String direction, String orderBy){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);

    }

    public Category fromDTO(CategoryDTO objDto){
        return new Category(objDto.getId(), objDto.getNome());
    }

}
