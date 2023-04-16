package ism.gestionsapprovisionnement.repositories;

import ism.gestionsapprovisionnement.entities.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneCommandeRepository  extends JpaRepository<LigneCommande,Long> {
}
