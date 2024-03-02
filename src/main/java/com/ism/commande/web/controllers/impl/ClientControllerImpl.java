package com.ism.commande.web.controllers.impl;

import com.ism.commande.data.entities.Client;
import com.ism.commande.data.repositories.ClientRepository;
import com.ism.commande.web.controllers.ClientController;
import com.ism.commande.web.dtos.ClientDtoForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequiredArgsConstructor
public class ClientControllerImpl  implements ClientController {
    private final ClientRepository clientRepository;
    @Override
    public String listerClient(Model model,
                               @RequestParam(name = "page",defaultValue = "0") int page,
                               @RequestParam(name = "size",defaultValue = "5") int size,
                               @RequestParam(name = "keyword",defaultValue = "") String keyword){
                Page<Client>   pageClients=  clientRepository.findByTelephoneContainsAndActiveTrueOrderByIdDesc(
                            keyword,
                            PageRequest.of(page,size)
                    );

                model.addAttribute("clients",pageClients.getContent());
                model.addAttribute("pages",new int[pageClients.getTotalPages()]);
                model.addAttribute("currentPage",page);
                model.addAttribute("keyword",keyword);
                return "client/client";
    }
    @Override
    public String index(){
        return "redirect:/api/v1/commande/liste-clients";
    }

    @GetMapping("admin/form-client")
    public String loadForClient(Model model){
        ClientDtoForm client=ClientDtoForm.builder().build();
        model.addAttribute("client",client);
        return "client/form.client";
    }

    @PostMapping("admin/save-client")
    public String saveClient(
            @Valid ClientDtoForm clientDtoForm,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){

            Map<String, String> errorsList = bindingResult.getFieldErrors()
                    .stream().collect(
                            Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage)
                    );
            redirectAttributes.addFlashAttribute("mode","error");
            redirectAttributes.addFlashAttribute("errorsList",errorsList);
            return "redirect:/admin/form-client";
        }else{
            redirectAttributes.addFlashAttribute("mode","succes");
            redirectAttributes.addFlashAttribute("message","Client Enregistre avec Success");
            Client client=clientDtoForm.toEntity();
             clientRepository.save(client);
             return "redirect:/liste-clients";
        }

    }

}

