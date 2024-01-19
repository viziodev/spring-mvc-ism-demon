package com.ism.commande.data.entities;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Adresse  {
    private String ville;
    private String quartier;
    private String numVilla;

    @Override
    public String toString() {
        return  ville + " | " + quartier + " | "   + numVilla  ;
    }
}
