package com.ism.commande.web.controllers.impl;

import com.ism.commande.data.entities.Client;
import com.ism.commande.data.entities.Commande;
import com.ism.commande.data.repositories.CommandeRepository;
import com.ism.commande.web.controllers.CommandeController;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CommandeControllerImpl implements CommandeController {
    private final CommandeRepository commandeRepository;

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
}


