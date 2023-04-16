package ism.gestionsapprovisionnement.repositories;

import ism.gestionsapprovisionnement.entities.Commande;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ism.gestionsapprovisionnement.entities.Client;
public interface CommandeRepository extends JpaRepository<Commande,Long> {
   public Page<Commande> getByClient(Client client, Pageable pageable);
   public Page<Commande> getByClientId(Long id, Pageable pageable);

}
