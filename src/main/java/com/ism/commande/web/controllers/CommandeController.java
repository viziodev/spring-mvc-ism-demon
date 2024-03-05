package com.ism.commande.web.controllers;

import com.ism.commande.web.dtos.PanierDto;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface CommandeController {
    @GetMapping("/client/liste-cmde-client")
    public String listerCommandeUnClient(
            Model model ,
            @RequestParam(name = "id",defaultValue = "") Long idClient,
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "size",defaultValue = "8") int size
    );

    @GetMapping("/admin/liste-all-cmde")
    public String listerAllCommande(Model model,
                                    @RequestParam(name = "page",defaultValue = "0") int page,
                                    @RequestParam(name = "size",defaultValue = "8") int size);

    @GetMapping ("/client/add-commande")
    public String saveCommande(Model model,
                               @ModelAttribute("panier") PanierDto panier
    );

    @GetMapping ("/client/detail-commande")
    public String detailCommande(Model model,
                                 @RequestParam(name = "id") Long idCommande
    );

    @GetMapping("/client/form-add-cmde-client")
    public String loadFormCommande(Model model,
                                   @RequestParam(name = "id",defaultValue = "") Long idClient,
                                   @ModelAttribute("panier") PanierDto panier

    );
}
