package com.ism.commande.security.services;


import com.ism.commande.security.entity.AppRole;
import com.ism.commande.security.entity.AppUser;

public interface SecurityService {
    AppUser getUserByUsername(String username);
    AppUser saveUser(String nomComplet,String username,String password);
    AppRole saveRole(String  roleName);
    void addRoleToUser(String username,String  roleName);
    void removeRoleToUser(String username,String  roleName);

}