package com.ism.commande.data.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ligne_commandes")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LigneCommande extends AbstractEntity {
    @Column(nullable = false)
    private double prix;
    @Column(nullable = false)
    private int quantite;
    @Column(nullable = false)
    private int montant;



    @ManyToOne
    @JoinColumn(name = "commande_id",nullable = false)
    Commande commande;
    @ManyToOne
    @JoinColumn(name = "article_id",nullable = false)
    Article article;


}
