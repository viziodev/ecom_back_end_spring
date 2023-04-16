package ism.gestionsapprovisionnement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    @Column(nullable = false,unique = true)
    private String libelle;
    @OneToMany(mappedBy = "categorie",fetch = FetchType.LAZY)
     private List<Article> articles;
}
