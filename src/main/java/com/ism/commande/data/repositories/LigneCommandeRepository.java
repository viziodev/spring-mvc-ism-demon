package com.ism.commande.data.repositories;

import com.ism.commande.data.entities.Categorie;
import com.ism.commande.data.entities.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneCommandeRepository extends JpaRepository<LigneCommande, Long> {
}
