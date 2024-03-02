package com.ism.commande.web.dtos;

import com.ism.commande.data.entities.Client;
import com.ism.commande.data.entities.Commande;
import com.ism.commande.data.enums.State;
import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Component
@Builder
public class PanierDto {
    private List<ArticlePanierDto> articles;
    private double total;
    private Client client;
    private State state = State.CREATE;

    public void calculMontant(double montant) {
        total += montant;
    }

    public void addArticleToPanier(ArticlePanierDto articlePanier) {
        OptionalInt OptPos = IntStream
                .range(0, articles.size() - 1)
                .filter(i ->
                        articles.get(i).getIdArticle() == articlePanier.getIdArticle()
                ).findFirst();
        if (!OptPos.isPresent()) {
            articles.add(articlePanier);
        } else {
            int pos = OptPos.getAsInt();
            articles.get(pos).calculQte(articlePanier.getQuantite());
        }
    }

    public   PanierDto toDto(Commande commande) {
        return PanierDto.builder()
                .total(commande.getMontant())
                .client(commande.getClient())
                .state(State.UPDATE)
                .articles(
                        commande.getLigneCommandes()
                                .stream()
                                .map(ligneCommande -> {
                                    return ArticlePanierDto.toDto(ligneCommande.getArticle());
                                }).toList())
                .build();

    }

}