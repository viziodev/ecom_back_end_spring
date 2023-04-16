package ism.gestionsapprovisionnement.entities;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Adresse {
    private String ville;
    private String quartier;
    private String numVilla;

    @Override
    public String toString() {
        return  ville + " | " + quartier + " | "   + numVilla  ;
    }
}
