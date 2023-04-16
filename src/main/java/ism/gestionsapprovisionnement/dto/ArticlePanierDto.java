package ism.gestionsapprovisionnement.dto;

import jakarta.persistence.Column;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticlePanierDto {
    private Long idArticle;
    private String Libelle;
    private double prix;
    private int quantite;
    private int montant;

    public int getMontant() {
        montant =(int) prix * quantite;
        return montant;
    }
    public int calculQte(int qte) {
        quantite+=qte;
        return quantite;
    }


}
