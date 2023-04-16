package ism.gestionsapprovisionnement.repositories;

import ism.gestionsapprovisionnement.entities.Commande;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ism.gestionsapprovisionnement.entities.Client;

import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande,Long> {
     Page<Commande> getByClient(Client client, Pageable pageable);
      Page<Commande> getByClientId(Long id, Pageable pageable);
      List<Commande> getByClient(Client client);

}
