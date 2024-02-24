package com.ism.commande.web.dtos;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticlePanierDto {
    private Long idArticle;
    private String libelle;
    private double prix;
    private int quantite;
    private int montant;

    public int getMontant() {
        montant =(int) prix * quantite;
        return montant;
    }
    public int calculQte(int qte) {
        quantite+=qte;
        return quantite;
    }


}