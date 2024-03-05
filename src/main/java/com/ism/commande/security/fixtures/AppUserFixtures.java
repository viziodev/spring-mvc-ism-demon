package com.ism.commande.security.fixtures;

import com.ism.commande.security.entity.AppUser;
import com.ism.commande.security.services.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(2)
public class AppUserFixtures implements CommandLineRunner {
    private final SecurityService service;
    @Override
    public void run(String... args) throws Exception {
        AppUser admin= service.saveUser("Birane Baila Wane","admin","passer");
        service.addRoleToUser("admin","Admin");
        service.addRoleToUser("admin","Client");

    }
}
