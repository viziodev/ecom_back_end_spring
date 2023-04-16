package ism.gestionsapprovisionnement.security.repositories;

import ism.gestionsapprovisionnement.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
  AppRole findByRoleName(String roleName);
}
