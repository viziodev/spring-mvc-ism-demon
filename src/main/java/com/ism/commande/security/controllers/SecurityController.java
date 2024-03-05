package com.ism.commande.security.controllers;

import com.ism.commande.security.entity.AppUser;
import com.ism.commande.security.services.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SecurityController {
    private final SecurityService service;
    @GetMapping("/")
    public String index(){
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String login(@AuthenticationPrincipal UserDetails userDetails)  {
            if (userDetails==null){
                 return "login";
            }

              if(userDetails.getAuthorities()
                     .stream()
                     .anyMatch(role->role.getAuthority().compareTo("Admin")==0)){
                      return "redirect:/admin/liste-clients";
             }
          if(userDetails.getAuthorities()
                .stream()
                .anyMatch(role->role.getAuthority().compareTo("Client")==0)){
                  AppUser user= service.getUserByUsername(userDetails.getUsername());
                  return "redirect:/client/liste-cmde-client?id="+user.getId();
         }
        return "login";
    }
}
