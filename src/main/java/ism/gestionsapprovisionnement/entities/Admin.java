package ism.gestionsapprovisionnement.entities;

import ism.gestionsapprovisionnement.security.entities.AppUser;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "admin")
@DiscriminatorValue(value = "Admin")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Admin  extends AppUser {
    private String poste;
}
