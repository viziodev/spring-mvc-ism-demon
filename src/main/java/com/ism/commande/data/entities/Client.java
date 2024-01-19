package com.ism.commande.data.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "clients")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Client extends AbstractEntity {

    @Column(nullable = false)
    private String nomComplet;
    @Column(nullable = false)
    private String telephone;
    @OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
    private List<Commande> commandes;


    @Embedded
    private Adresse adresse;




}
