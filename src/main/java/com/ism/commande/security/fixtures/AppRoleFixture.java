package com.ism.commande.security.fixtures;

import com.ism.commande.security.entity.AppRole;
import com.ism.commande.security.services.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(1)
public class AppRoleFixture implements CommandLineRunner {
    private final SecurityService service;
    @Override
    public void run(String... args) throws Exception {
        AppRole role1=service.saveRole("Client");
        AppRole role2=service.saveRole("Admin");
    }
}
