package com.ism.commande.web.controllers;

import com.ism.commande.web.dtos.ClientDtoForm;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface ClientController {
    @GetMapping("/admin/liste-clients")
    String listerClient(Model model,
                               @RequestParam(name = "page",defaultValue = "0") int page,
                               @RequestParam(name = "size",defaultValue = "5") int size,
                               @RequestParam(name = "keyword",defaultValue = "") String keyword);
    @GetMapping("/admin/form-client")
    String loadForClient(Model model);

    @PostMapping("admin/save-client")
    public String saveClient(
            @Valid ClientDtoForm clientDtoForm,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes);
}


