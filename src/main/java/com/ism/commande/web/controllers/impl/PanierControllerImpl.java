package com.ism.commande.web.controllers.impl;

import com.ism.commande.data.entities.Article;
import com.ism.commande.data.repositories.ArticleRepository;
import com.ism.commande.web.controllers.PanierController;
import com.ism.commande.web.dtos.ArticlePanierDto;
import com.ism.commande.web.dtos.PanierDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@RequiredArgsConstructor
@SessionAttributes({"panier"})
public class PanierControllerImpl implements PanierController {

    private final ArticleRepository articleRepository;


    @PostMapping("/add-panier")
    public String addArticleToPanier(Model model,
                                     @Valid ArticlePanierDto articlePanier,
                                     @ModelAttribute("panier") PanierDto panier){
        Article article=articleRepository.findById(articlePanier.getIdArticle()).get();
        articlePanier.setLibelle(article.getLibelle());
        panier.addArticleToPanier(articlePanier);
        panier.calculMontant(articlePanier.getMontant());
        model.addAttribute("panier",panier);
        return "redirect:/form-add-cmde-client?id="+panier.getClient().getId();
    }


}
