package com.ism.commande.data.repositories;

import com.ism.commande.data.entities.Categorie;
import com.ism.commande.data.entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
}
