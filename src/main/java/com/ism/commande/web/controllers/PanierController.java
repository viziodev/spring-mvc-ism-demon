package com.ism.commande.web.controllers;

import com.ism.commande.web.dtos.ArticlePanierDto;
import com.ism.commande.web.dtos.PanierDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


public interface PanierController {
    @PostMapping("/add-panier")
    public String addArticleToPanier(Model model,
                                     ArticlePanierDto articlePanier,
                                     @ModelAttribute("panier") PanierDto panier, HttpServletRequest request
    );
}
