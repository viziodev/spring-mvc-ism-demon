package com.ism.commande.web.dtos;


import com.ism.commande.data.entities.Adresse;
import com.ism.commande.data.entities.Client;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDtoForm {
    protected Long id;
    @NotEmpty(message = "Le Nom est Obligatoire")
    protected String nomComplet;
    //@NotEmpty(message = "Le Telephone est Obligatoire")
    @Pattern(regexp = "[0-9]{9}", message = "Le Telephone doit avoir 9 chiffres")
    private String telephone;
    private String ville;
    private String quartier;
    private String numVilla;

    public Client toEntity(){
        return new Client(
                nomComplet,
                telephone,
                null,
                new Adresse(
                        ville,
                        quartier,
                        numVilla
                )
        );
    }

    public ClientDtoForm toDto(Client client){
        if(client==null)  return null;
        return ClientDtoForm.builder()
                .id(client.getId())
                .nomComplet(client.getNomComplet())
                .telephone(client.getTelephone())
                .numVilla(client.getAdresse().getNumVilla())
                .ville(client.getAdresse().getVille())
                .quartier(client.getAdresse().getQuartier())
                .build();
    }
}
