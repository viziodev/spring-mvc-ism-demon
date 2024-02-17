package com.ism.commande.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EtatCommande {
    Encours(0),
    Terminer(1),
    Facturer(2),
    Payer(3);
    private final long etatIndex;
}
