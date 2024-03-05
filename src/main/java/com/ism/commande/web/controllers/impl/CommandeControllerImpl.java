package com.ism.commande.web.controllers.impl;

import com.ism.commande.data.entities.Article;
import com.ism.commande.data.entities.Client;
import com.ism.commande.data.entities.Commande;
import com.ism.commande.data.enums.State;
import com.ism.commande.data.repositories.ArticleRepository;
import com.ism.commande.data.repositories.ClientRepository;
import com.ism.commande.data.repositories.CommandeRepository;
import com.ism.commande.services.CommandeService;
import com.ism.commande.web.controllers.CommandeController;
import com.ism.commande.web.dtos.ArticlePanierDto;
import com.ism.commande.web.dtos.PanierDto;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@SessionAttributes({"panier"})
public class CommandeControllerImpl implements CommandeController {
    private final CommandeRepository commandeRepository;
    private final ClientRepository clientRepository;
    private final ArticleRepository articleRepository;

    private final CommandeService commandeService;
    public String listerCommandeUnClient(
            Model model ,
            @RequestParam(name = "id",defaultValue = "") Long idClient,
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "size",defaultValue = "8") int size
    ){

        Page<Commande> commandePage=commandeRepository.getByClientId(idClient, PageRequest.of(page,size));
        model.addAttribute("listCommandes",commandePage.getContent());
        model.addAttribute("pages",new int[commandePage.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("idClient",idClient);
        return "commande" ;
    }

    public String listerAllCommande(Model model,
                                    @RequestParam(name = "page",defaultValue = "0") int page,
                                    @RequestParam(name = "size",defaultValue = "8") int size){
        Page<Commande> commandePage=commandeRepository.findAll(PageRequest.of(page,size));
        model.addAttribute("listCommandes",commandePage.getContent());
        model.addAttribute("pages",new int[commandePage.getTotalPages()]);
        model.addAttribute("currentPage",page);
        return "commande" ;
    }

    public String loadFormCommande(Model model,
                                   @RequestParam(name = "id",defaultValue = "") Long idClient,
                                   @ModelAttribute("panier") PanierDto panier

    ){
        Optional<Client> OpClient= clientRepository.findById(idClient);
        if(!OpClient.isPresent()){
            return "redirect:/admin/liste-clients";
        }
        List<Article> articleList=articleRepository.findAll();
        panier.setClient(OpClient.get());
        model.addAttribute("panier",panier);
        model.addAttribute("articles",articleList);
        model.addAttribute("articlePanier",new ArticlePanierDto());
        return "form.commande";
    }
    public String saveCommande(Model model,
                               @ModelAttribute("panier") PanierDto panier
    ){
        commandeService.saveCommande(panier);
        //Vider le Panier
        model.addAttribute("panier",panier());
        return "redirect:/client/liste-cmde-client?id="+panier.getClient().getId();
    }

    @Override
    public String detailCommande(Model model,
                                 @RequestParam(name = "id") Long idCommande) {
          Commande commande=  commandeRepository.findById(idCommande).orElseThrow(()->new IllegalArgumentException("Invalid"));
          PanierDto panierDto= PanierDto.toDto(commande);
          panierDto.setState(State.UPDATE);
          model.addAttribute("panier",panierDto);
         return "detail.commande";
     }

     @ModelAttribute("panier")
      public PanierDto panier() {
        return new PanierDto(
                new ArrayList<>(),
                0,
                null,
                State.CREATE
         );
    }

}


