package com.ism.commande.web.dtos;

import com.ism.commande.data.entities.Client;
import lombok.*;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PanierDto {
    private List<ArticlePanierDto> articles;
    private double total;
    private Client client;

    public void calculMontant(double montant){
        total+=montant;
    }
    public void addArticleToPanier(ArticlePanierDto articlePanier){
        OptionalInt OptPos= IntStream
                .range(0, articles.size())
                .filter(i ->
                        articles.get(i).getIdArticle()==articlePanier.getIdArticle()
                ) .findFirst();
        if(!OptPos.isPresent()){
            articles.add(articlePanier) ;
        }else{
            int pos=OptPos.getAsInt();
            articles.get(pos).calculQte(articlePanier.getQuantite());
        }
    }

}