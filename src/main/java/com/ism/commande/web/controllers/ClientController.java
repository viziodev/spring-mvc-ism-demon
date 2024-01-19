package com.ism.commande.web.controllers;

import com.ism.commande.data.entities.Client;
import com.ism.commande.data.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ClientController {
    private final ClientRepository clientRepository;
    @GetMapping("/liste-clients")
    public String listerClient(Model model,
                               @RequestParam(name = "page",defaultValue = "0") int page,
                               @RequestParam(name = "size",defaultValue = "5") int size){
        Page<Client> pageClients=  clientRepository.findAllByActiveTrue(
                PageRequest.of(page,size)
        );

        model.addAttribute("clients",pageClients.getContent());
        model.addAttribute("pages",new int[pageClients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        return "client";
    }
}

