package com.ism.commande.web.controllers.impl;

import com.ism.commande.data.entities.Article;
import com.ism.commande.data.entities.Client;
import com.ism.commande.data.entities.Commande;
import com.ism.commande.data.repositories.ArticleRepository;
import com.ism.commande.data.repositories.ClientRepository;
import com.ism.commande.data.repositories.CommandeRepository;
import com.ism.commande.web.controllers.CommandeController;
import com.ism.commande.web.dtos.ArticlePanierDto;
import com.ism.commande.web.dtos.ClientPanierDto;
import com.ism.commande.web.dtos.PanierDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CommandeControllerImpl implements CommandeController {
    private final CommandeRepository commandeRepository;
    private final ClientRepository clientRepository;
    private final ArticleRepository articleRepository;
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

    @GetMapping("/form-add-cmde-client")
    public String loadFormCommande(Model model,
                                   @RequestParam(name = "id",defaultValue = "") Long idClient,
                                   @ModelAttribute("panier") PanierDto panier,
                                   HttpSession session
    ){

        Optional<Client> OpClient= clientRepository.findById(idClient);
        if(!OpClient.isPresent()){
            return "redirect:/liste-clients";
        }
        @SuppressWarnings("unchecked")
        List<Article> articleList=articleRepository.findAll();
        //   model.addAttribute("client",OpClient.get());
        panier.setClient(OpClient.get());
        model.addAttribute("panier",panier);
        //Afficher les infos sur le Select
        model.addAttribute("articles",articleList);
        //Mapping du formulaire Ajout dans le Panier
        model.addAttribute("articlePanier",new ArticlePanierDto());

        return "form.commande";
    }
}


