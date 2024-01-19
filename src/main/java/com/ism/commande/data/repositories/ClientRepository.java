package com.ism.commande.data.repositories;

import com.ism.commande.data.entities.Article;
import com.ism.commande.data.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByTelephone(String telephone);
    List<Client> findByNomCompletLike(String telephone);

}
