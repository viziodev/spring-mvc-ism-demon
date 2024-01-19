package com.ism.commande.data.repositories;

import com.ism.commande.data.entities.Article;
import com.ism.commande.data.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Page<Client> findAllByActiveTrue(Pageable pageable);
    Page<Client> findByTelephoneContainsAndActiveTrue(String telephone,
                                         Pageable pageable);
}
