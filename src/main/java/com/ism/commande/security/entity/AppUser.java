package com.ism.commande.security.entity;

import com.ism.commande.data.entities.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "role")
@DiscriminatorValue(value = "Admin")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public  class AppUser extends AbstractEntity {
    @Column(nullable = false)
    protected String nomComplet;
    @Column(nullable = false,unique = true)
    protected String username;
    @Column(nullable = false)
    protected String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id")
    )
    List<AppRole> roles=new ArrayList<>();
}