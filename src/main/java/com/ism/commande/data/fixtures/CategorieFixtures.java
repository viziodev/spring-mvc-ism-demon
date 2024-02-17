package com.ism.commande.data.fixtures;

import com.ism.commande.data.entities.Categorie;
import com.ism.commande.data.repositories.CategorieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(1)
public class CategorieFixtures implements CommandLineRunner {
    private final CategorieRepository categorieRepository;
    @Override
    public void run(String... args) throws Exception {
        for (int i = 1; i <= 4; i++) {
            categorieRepository.save(new Categorie("Categorie"+i,null));
        }
    }
}
