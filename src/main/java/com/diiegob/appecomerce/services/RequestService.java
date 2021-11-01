package com.diiegob.appecomerce.services;

import com.diiegob.appecomerce.domain.Request;
import com.diiegob.appecomerce.repositories.CategoryRepository;
import com.diiegob.appecomerce.repositories.RequestRepository;
import com.diiegob.appecomerce.services.exceptions.ObjNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    private RequestRepository repo;

    public Request requestById(Integer id){
        Optional<Request> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjNotFoundException(
                "Objeto não encontrado! ID: " + id + " , Tipo: "+ Request.class.getName()));
    }
}
