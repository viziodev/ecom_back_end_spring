package ism.gestionsapprovisionnement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "articles")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    @Column(nullable = false,unique = true)
    private String libelle;
    @OneToMany(mappedBy = "article")
    private List<LigneCommande> ligneCommandes;
    public Article(Long id) {
        this.id = id;
    }

    @Column()
    private double ancienPrice;
    @Column()
    private double nouveauPrice;
    @Column()
    private String photo;
    @Column()
    private boolean promo;
    @Column()
    private double qteStock;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;
}



