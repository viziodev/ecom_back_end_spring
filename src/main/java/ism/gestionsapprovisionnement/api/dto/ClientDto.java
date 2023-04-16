package ism.gestionsapprovisionnement.api.dto;

import ism.gestionsapprovisionnement.entities.Client;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {

       private Long id;
       private String  nomComplet;
       private String   login;
       private String   password;
       private List<String > roles;

       public ClientDto(Client client) {
              id= client.getId();
              nomComplet=client.getNomComplet();
              login=client.getUsername();
              roles=client.getRoles()
                      .stream()
                      .map(role->role.getRoleName())
                      .collect(Collectors.toList());
       }
}
