package com.ism.commande.web.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface ClientController {
    @GetMapping("/liste-clients")
   String listerClient(Model model,
                               @RequestParam(name = "page",defaultValue = "0") int page,
                               @RequestParam(name = "size",defaultValue = "5") int size,
                               @RequestParam(name = "keyword",defaultValue = "") String keyword);
    @GetMapping("/")
     String index();
}
