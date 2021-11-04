package com.diiegob.appecomerce.services.validation;

import com.diiegob.appecomerce.domain.Client;
import com.diiegob.appecomerce.dto.ClientDTO;
import com.diiegob.appecomerce.repositories.ClientRepository;
import com.diiegob.appecomerce.resources.exception.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ClientRepository repo;

    @Override
    public void initialize(ClientUpdate ann) {
    }

    @Override
    public boolean isValid(ClientDTO objDto, ConstraintValidatorContext context) {

        @SuppressWarnings("unchecked")
        //busca o id do cliente no URL
        Map<String, String> map =(Map <String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));//converte id recebido para um inteiro

        List<FieldMessage> list = new ArrayList<>();

        Client aux = repo.findByEmail(objDto.getEmail());
        if (aux != null && !aux.getId().equals(uriId)) {
            list.add(new FieldMessage("email", "E-mail já cadastrado!"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getField())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
