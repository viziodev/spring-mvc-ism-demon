package com.ism.commande.data.entities;

import com.ism.commande.data.enums.EtatCommande;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "commandes")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Commande extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateComd;
    private double montant;
    @Enumerated(value = EnumType.STRING)
    private EtatCommande etat;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Embedded
    private Adresse adresse;
    @OneToMany(mappedBy = "commande")
    private List<LigneCommande> ligneCommandes;

    public Commande(Long id, Date dateComd, double montant, EtatCommande etat, Client client, Adresse adresse) {
        this.id = id;
        this.dateComd = dateComd;
        this.montant = montant;
        this.etat = etat;
        this.client = client;
        this.adresse = adresse;
    }
}