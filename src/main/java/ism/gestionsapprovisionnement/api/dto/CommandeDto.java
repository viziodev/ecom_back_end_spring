package ism.gestionsapprovisionnement.api.dto;

import ism.gestionsapprovisionnement.entities.Commande;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommandeDto {
    private final double tva= 0.18;
    private Long id;
    private List<ProductDto> products;
    private ClientDto client;
    private double totalTtc;
    private double totalHtc;
    private Date date;

    public CommandeDto(Commande commande) {
        id=commande.getId();
        products=commande
                .getLigneCommandes()
                .stream()
                .map(ligneCommande -> new ProductDto(ligneCommande))
                .collect(Collectors.toList());
                client=new ClientDto(commande.getClient());
         totalHtc=commande.getMontant();
         totalTtc=commande.getMontant()*(tva+1);
         date=commande.getDateComd();

    }
}
