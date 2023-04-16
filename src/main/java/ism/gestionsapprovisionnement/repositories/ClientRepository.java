package ism.gestionsapprovisionnement.repositories;

import ism.gestionsapprovisionnement.entities.Client;
import ism.gestionsapprovisionnement.security.entities.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Long> {
     Client findByTelephone(String tel);
     Page<Client> findByTelephoneContains(String tel, Pageable pageable);
     AppUser findByUsername(String username);
}
