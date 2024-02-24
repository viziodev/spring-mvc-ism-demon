package com.ism.commande.web.dtos;


import com.ism.commande.data.entities.Adresse;
import com.ism.commande.data.entities.Client;
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

    public static ClientPanierDto toDto(Client client) {
        return new ClientPanierDto(
                null,
                client.getNomComplet(),
                client.getTelephone(),
                client.getAdresse().getVille(),
                client.getAdresse().getQuartier(),
                client.getAdresse().getNumVilla()
        );
    }
}
