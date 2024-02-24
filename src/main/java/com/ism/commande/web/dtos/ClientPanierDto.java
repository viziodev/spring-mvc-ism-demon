package com.ism.commande.web.dtos;


import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientPanierDto {
    protected Long id;
    protected String nomComplet;
    private String telephone;
    private String ville;
    private String quartier;
    private String numVilla;
}
