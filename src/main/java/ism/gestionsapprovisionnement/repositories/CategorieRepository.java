package ism.gestionsapprovisionnement.repositories;

import ism.gestionsapprovisionnement.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie,Long> {

}
