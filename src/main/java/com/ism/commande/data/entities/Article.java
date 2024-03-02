package com.ism.commande.data.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "articles")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Article   extends AbstractEntity{
    @Column(nullable = false,unique = true)
    private String libelle;
    @OneToMany(mappedBy = "article")
    private List<LigneCommande> ligneCommandes;


    @Column()
    private double ancienPrice;
    @Column()
    private double nouveauPrice;
    @Column()
    private String photo;
    @Column()
    private boolean promo;
    @Column()
    private double qteStock;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    public Article(Long idArticle) {
        super(idArticle);
    }
}