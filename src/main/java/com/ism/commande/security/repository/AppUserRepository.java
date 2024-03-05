package com.ism.commande.security.repository;

import com.ism.commande.security.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository  extends JpaRepository<AppUser,Long> {
    AppUser findByUsername(String username);
}
