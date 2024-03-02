package com.ism.commande.services.impl;

import com.ism.commande.data.entities.Article;
import com.ism.commande.data.entities.Commande;
import com.ism.commande.data.entities.LigneCommande;
import com.ism.commande.data.enums.EtatCommande;
import com.ism.commande.data.repositories.ArticleRepository;
import com.ism.commande.data.repositories.CommandeRepository;
import com.ism.commande.data.repositories.LigneCommandeRepository;
import com.ism.commande.services.CommandeService;
import com.ism.commande.web.dtos.PanierDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
public class CommandeServiceImpl implements CommandeService {

    private final CommandeRepository commandeRepository;
    private final LigneCommandeRepository ligneCommandeRepository;
    private final ArticleRepository articleRepository;

    public void saveCommande(PanierDto panier){
         Commande commande=new Commande(null
                ,new Date(),
                panier.getTotal(),
                EtatCommande.Encours,
                panier.getClient(),
                panier.getClient().getAdresse());
                commandeRepository.save(commande);
                panier.getArticles().stream().forEach(articlePanier->{
                  Article article=  articleRepository.findById(articlePanier.getIdArticle()).orElseThrow(() ->new RuntimeException("Cet article existe pas"));
                 ligneCommandeRepository.save(
                    new LigneCommande(
                            articlePanier.getPrix(),
                            articlePanier.getQuantite()
                            ,articlePanier.getMontant(),
                            commande,
                            article));
                });
    }
}
