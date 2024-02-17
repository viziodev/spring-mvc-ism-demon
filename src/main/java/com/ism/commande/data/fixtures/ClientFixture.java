package com.ism.commande.data.fixtures;

import com.ism.commande.data.entities.Adresse;
import com.ism.commande.data.entities.Client;
import com.ism.commande.data.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
@RequiredArgsConstructor
public class ClientFixture implements CommandLineRunner {
    private final ClientRepository clientRepository;

    @Override
    public void run(String... args) throws Exception {
        for (int i = 1; i <= 10; i++) {
            Client client = new Client();
            if (i % 2 == 0) {
                client.setNomComplet("Diop " + i);
                client.setTelephone("77867101" + i);
            } else {

                client.setNomComplet("Ndiaye" + i);
                client.setTelephone("77867101" + i);
                client.setActive(false);
            }
            client.setAdresse(new Adresse("Dakar", "Point E", "Villa00" + i));
            clientRepository.save(client);
        }
    }
}
