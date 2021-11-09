package com.diiegob.appecomerce.repositories;

import com.diiegob.appecomerce.domain.Category;
import com.diiegob.appecomerce.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Transactional(readOnly = true)
//    @Query("SELECT DISTINCT obj FROM Product obj INNER JOIN obj.categories cat WHERE obj.nome LIKE %:nome% AND cat IN :categories")
    Page<Product> findDistinctByNomeContainingAndCategoriesIn(String nome, List<Category> categories, Pageable pageRequest);
}