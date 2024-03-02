package com.ism.commande.web.dtos;

import com.ism.commande.data.entities.Article;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticlePanierDto {
    private Long idArticle;
    private String libelle;
   // @Pattern(regexp = "[0-9]+",message = "La quantite doit etre des chiffres")
    @Positive(message = "La quantite doit etre positif")
    private double prix;
    @Positive(message = "La quantite doit etre positif")
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

    public static ArticlePanierDto toDto(Article article) {
        return ArticlePanierDto
                .builder()
                .idArticle(article.getId())
                .libelle(article.getLibelle())
                .prix(article.getNouveauPrice())
                .quantite(12000)
                .montant(120000)
                .build();
    }


}