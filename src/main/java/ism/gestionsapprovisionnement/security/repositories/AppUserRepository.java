package ism.gestionsapprovisionnement.security.repositories;

import ism.gestionsapprovisionnement.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository  extends JpaRepository<AppUser,Long> {
    AppUser findByUsername(String username);
}
