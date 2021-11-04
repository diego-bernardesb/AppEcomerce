package com.diiegob.appecomerce.services;

import com.diiegob.appecomerce.domain.Address;
import com.diiegob.appecomerce.domain.City;
import com.diiegob.appecomerce.domain.Client;
import com.diiegob.appecomerce.domain.enuns.TypeClient;
import com.diiegob.appecomerce.dto.ClientDTO;
import com.diiegob.appecomerce.dto.ClientNewDTO;
import com.diiegob.appecomerce.repositories.AddressRepository;
import com.diiegob.appecomerce.repositories.ClientRepository;
import com.diiegob.appecomerce.services.exceptions.DataIntegrityViolationException;
import com.diiegob.appecomerce.services.exceptions.ObjNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repo;
    @Autowired
    private AddressRepository addressRepository;

    public Client find(Integer id){
        Optional<Client> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjNotFoundException(
                "Cliente não encontrado! ID: " + id +", Tipo: " + Client.class.getName()));
    }

    @Transactional
    public Client insert(Client obj){
        obj.setId(null);
        repo.save(obj);
        addressRepository.saveAll(obj.getEnderecos());
        return obj;
    }

    public Client update(Client obj){
        //instancia um newObj atraves do banco de dados
        Client newObj = find(obj.getId());//reutiliza o codigo get para confirmar o objeto
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    public void delete(Integer id) {
        find(id);
        //exceção personalisada para não estourar error 5xx
        try {
            repo.deleteById(id);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Não é possivel deletar pq há pedidos relacionados");
        }
    }

    public List<Client> findAll(){
        return repo.findAll();
    }

    public Page<Client> findPage(Integer page, Integer linesPerPage, String direction, String orderBy){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);

    }

    public Client fromDTO(ClientDTO objDto){
        return new Client(objDto.getId(), objDto.getNome(), objDto.getEmail(), null,null);
    }

    public Client fromDTO(ClientNewDTO objDto){
        Client cli = new Client(null, objDto.getNome(),objDto.getEmail(), objDto.getCpfOuCnpj(), TypeClient.toEnun(objDto.getTipo()));
        City cid = new City(objDto.getCidadeId(), null, null );
        Address end = new Address(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),objDto.getBairro(),objDto.getCep(),cli, cid);
        cli.getEnderecos().add(end);
        cli.getPhones().add(objDto.getTelefone1());
        if(objDto.getTelefone2() != null){
            cli.getPhones().add(objDto.getTelefone2());
        }
        if(objDto.getTelefone3() != null){
            cli.getPhones().add(objDto.getTelefone3());
        }

        return cli;
    }

    private void updateData(Client newObj, Client obj){
        //atualiza apenas os dados recebidos pelo obj
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }
}
