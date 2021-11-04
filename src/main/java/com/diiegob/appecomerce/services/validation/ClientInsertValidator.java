package com.diiegob.appecomerce.services.validation;

import com.diiegob.appecomerce.domain.Client;
import com.diiegob.appecomerce.domain.enuns.TypeClient;
import com.diiegob.appecomerce.dto.ClientNewDTO;
import com.diiegob.appecomerce.repositories.ClientRepository;
import com.diiegob.appecomerce.resources.exception.FieldMessage;
import com.diiegob.appecomerce.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {

    @Autowired
    ClientRepository repo;

    @Override
    public void initialize(ClientInsert ann) {
    }

    @Override
    public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        if (objDto.getTipo().equals(TypeClient.PESSOAFISICA.getCodigo()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
        }

        if (objDto.getTipo().equals(TypeClient.PESSOAJURIDICA.getCodigo()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
        }

        Client aux = repo.findByEmail(objDto.getEmail());
        if (aux != null) list.add(new FieldMessage("email", "E-mail já cadastrado!"));

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getField())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
