package ism.gestionsapprovisionnement.dto;

import ism.gestionsapprovisionnement.entities.Adresse;
import ism.gestionsapprovisionnement.entities.Client;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDtoForm {

    protected Long id;
    @NotEmpty(message = "Le Nom est Obligatoire")
    protected String nomComplet;
    @NotEmpty(message = "Le Telephone est Obligatoire")
    private String telephone;
    private String ville;
    private String quartier;
    private String numVilla;

    public Client toEntity(){
        return new Client(
                id,
                nomComplet,
                telephone,
                 new Adresse(
                         ville,
                         quartier,
                         numVilla
                 )
        );
    }

    public ClientDtoForm toDto(Client client){
      if(client==null)  return null;
      return ClientDtoForm.builder()
              .id(client.getId())
              .nomComplet(client.getNomComplet())
              .telephone(client.getTelephone())
              .numVilla(client.getAdresse().getNumVilla())
              .ville(client.getAdresse().getVille())
              .quartier(client.getAdresse().getQuartier())
              .build();
    }
}
