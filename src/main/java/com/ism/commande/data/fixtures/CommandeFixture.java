package com.ism.commande.data.fixtures;

import com.ism.commande.data.entities.*;
import com.ism.commande.data.enums.EtatCommande;
import com.ism.commande.data.repositories.ArticleRepository;
import com.ism.commande.data.repositories.ClientRepository;
import com.ism.commande.data.repositories.CommandeRepository;
import com.ism.commande.data.repositories.LigneCommandeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Order(Ordered.LOWEST_PRECEDENCE)
public class CommandeFixture   implements CommandLineRunner {
    private final ClientRepository clientRepository;
    private final ArticleRepository articleRepository;
    private final CommandeRepository commandeRepository;
    private final LigneCommandeRepository ligneCommandeRepository;
    @Override
    public void run(String... args) throws Exception {
        for (int i = 1; i <= 10; i++) {
            Client cl=clientRepository.findByTelephoneAndActiveTrue("77867101"+i);
            Article article=articleRepository.findById(Long.valueOf(i)).get();
            Adresse adresse=new Adresse();
            adresse.setVille("Ville"+i);
            adresse.setQuartier("Quartier"+i);
            adresse.setNumVilla("Num00"+i);
            Commande commande=new Commande(null
                    , new Date(),
                    100000,
                    EtatCommande.Encours,cl,
                    adresse);
            commandeRepository.save(commande);
            ligneCommandeRepository.save(
                    new LigneCommande(
                            10000,
                            10000*100,100,
                            commande,
                            article));
        }
    }
}
