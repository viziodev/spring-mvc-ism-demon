package com.ism.commande.data.entities;


import com.ism.commande.security.entity.AppUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "clients")
@DiscriminatorValue(value = "Client")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Client extends AppUser {
    @Column(nullable = false)
    private String telephone;
    @OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
    private List<Commande> commandes;
    @Embedded
    private Adresse adresse;
    public Client(String nomComplet, String telephone, Adresse adresse) {
          this.nomComplet=nomComplet;
          this.telephone=telephone;
          this.adresse=adresse;
    }
}
