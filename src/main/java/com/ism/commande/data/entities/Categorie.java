package com.ism.commande.data.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Categorie extends AbstractEntity{
    @Column(nullable = false,unique = true)
    private String libelle;
    @OneToMany(mappedBy = "categorie",fetch = FetchType.LAZY)
    private List<Article> articles;
}