package com.diiegob.appecomerce.resources;

import com.diiegob.appecomerce.domain.Client;
import com.diiegob.appecomerce.domain.Client;
import com.diiegob.appecomerce.dto.ClientDTO;
import com.diiegob.appecomerce.dto.ClientDTO;
import com.diiegob.appecomerce.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

    @Autowired
    ClientService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Client> find(@PathVariable Integer id){

        Client obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody ClientDTO objDto) {
        Client obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody ClientDTO objDto, @PathVariable Integer id) {
        Client obj = service.fromDTO(objDto);
        obj.setId(id);
        service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll() {
        List<Client> list = service.findAll();
        List<ClientDTO> listDto = list.stream().map(obj -> new ClientDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<ClientDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {
        Page<Client> list = service.findPage(page, linesPerPage, direction, orderBy);
        Page<ClientDTO> listDto = list.map(obj -> new ClientDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }
}
