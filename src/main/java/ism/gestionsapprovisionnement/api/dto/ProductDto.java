package ism.gestionsapprovisionnement.api.dto;

import ism.gestionsapprovisionnement.entities.Article;
import ism.gestionsapprovisionnement.entities.LigneCommande;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
   private Long id;
    private String libelle;
    private double oldPrice;
    private double newPrice;
    private String image;
    private boolean saled;
    private String categorie;
    private double qteStock;
    private double qteComd;

    private double montant;
    public ProductDto(Article article) {
        id=article.getId();
        libelle=article.getLibelle();
        newPrice=article.getNouveauPrice();
        oldPrice=article.getAncienPrice();
        categorie =article.getCategorie().getLibelle();
        saled=article.isPromo();
        image= article.getPhoto();
        qteStock=article.getQteStock();
    }
    public ProductDto(LigneCommande ligneCommande) {
        id=ligneCommande.getArticle().getId();
        libelle=ligneCommande.getArticle().getLibelle();
        newPrice=ligneCommande.getArticle().getNouveauPrice();
        oldPrice=ligneCommande.getArticle().getAncienPrice();
        categorie =ligneCommande.getArticle().getCategorie().getLibelle();
        saled=ligneCommande.getArticle().isPromo();
        image= ligneCommande.getArticle().getPhoto();
        qteStock=ligneCommande.getArticle().getQteStock();
        qteComd=ligneCommande.getQuantite();
        montant=ligneCommande.getMontant();
    }


}
