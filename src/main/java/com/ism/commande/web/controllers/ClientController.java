package com.ism.commande.web.controllers;

import com.ism.commande.data.entities.Client;
import com.ism.commande.data.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ClientController {
    private final ClientRepository clientRepository;
    @GetMapping("/liste-clients")
    public String listerClient(Model model){
        List<Client> clients=  clientRepository.findAll( );
        model.addAttribute("clients",clients);
        return "client";
    }
}

