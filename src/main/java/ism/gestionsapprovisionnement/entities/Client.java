package ism.gestionsapprovisionnement.entities;

import ism.gestionsapprovisionnement.security.entities.AppUser;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "clients")
@DiscriminatorValue(value = "Client")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Client extends AppUser {

    @Column(nullable = false)
    private String telephone;
    @OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
    private List<Commande> commandes;

    public Client(Long id, String nomComplet, String telephone, Adresse adresse) {
        super(id, nomComplet);
        this.telephone = telephone;
        this.adresse = adresse;
    }




    @Embedded
    private Adresse adresse;

    @Override
    public String toString() {
        return "Client{" +
                "telephone='" + telephone + '\'' +
                ", commandes=" + commandes +
                ", adresse=" + adresse +
                ", nomComplet='" + nomComplet + '\'' +
                '}';
    }

    public Client(Long id) {
         super(id);
    }
}
