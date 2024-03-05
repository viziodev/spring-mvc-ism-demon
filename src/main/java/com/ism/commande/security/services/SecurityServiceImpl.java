package com.ism.commande.security.services;

import com.ism.commande.security.entity.AppRole;
import com.ism.commande.security.entity.AppUser;
import com.ism.commande.security.repository.AppRoleRepository;
import com.ism.commande.security.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService{

    private final AppRoleRepository appRoleRepository;
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;



    @Override
    public AppUser getUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    @Override
    public AppUser saveUser(String nomComplet, String username, String password) {
        AppUser user=appUserRepository.findByUsername(username);
        if(user!=null) throw  new RuntimeException("Cet utilisateur existe deja ");
        user=new AppUser();
        user.setNomComplet(nomComplet);
        user.setPassword(passwordEncoder.encode(password));
        user.setUsername(username);
        return appUserRepository.save(user);
    }

    @Override
    public AppRole saveRole(String roleName) {
        AppRole role=new AppRole(null,roleName,null);
        return appRoleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser user=appUserRepository.findByUsername(username);
        if(user==null) throw  new RuntimeException("Cet utilisateur n'existe pas ");
        AppRole role =appRoleRepository.findByRoleName(roleName);
        if(role==null) throw  new RuntimeException("Cet Role n'existe pas ");
        user.getRoles().add(role);
        appUserRepository.save(user);
    }

    @Override
    public void removeRoleToUser(String username, String roleName) {
        AppUser user=appUserRepository.findByUsername(username);
        if(user==null) throw  new RuntimeException("Cet utilisateur n'existe pas ");
        AppRole role =appRoleRepository.findByRoleName(roleName);
        if(role==null) throw  new RuntimeException("Cet Role n'existe pas ");
        user.getRoles().remove(role);
        appUserRepository.save(user);
    }
}
