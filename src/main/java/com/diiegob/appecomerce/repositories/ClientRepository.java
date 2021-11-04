package com.diiegob.appecomerce.repositories;

import com.diiegob.appecomerce.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Client findByEmail(String email);//O spring cria a função buscar por e-mail
}
