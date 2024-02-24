package com.ism.commande.web.controllers.impl;

import com.ism.commande.data.entities.Article;
import com.ism.commande.data.repositories.ArticleRepository;
import com.ism.commande.web.controllers.PanierController;
import com.ism.commande.web.dtos.ArticlePanierDto;
import com.ism.commande.web.dtos.PanierDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PanierControllerImpl implements PanierController {

    private final ArticleRepository articleRepository;
    @PostMapping("/add-panier")
    public String addArticleToPanier(Model model,
                                     ArticlePanierDto articlePanier,
                                     @ModelAttribute("panier") PanierDto panier,
    HttpServletRequest request){
        Article article=articleRepository.findById(articlePanier.getIdArticle()).get();
        articlePanier.setLibelle(article.getLibelle());
        // panier.getArticles().add(articlePanier);
        panier.addArticleToPanier(articlePanier);
        panier.calculMontant(articlePanier.getMontant());
        model.addAttribute("panier",panier);
        return "redirect:/form-add-cmde-client?id="+panier.getClient().getId();
    }
}
