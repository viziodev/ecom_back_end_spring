package ism.gestionsapprovisionnement.api.services;

import ism.gestionsapprovisionnement.api.dto.CommandeDto;
import ism.gestionsapprovisionnement.entities.Client;
import ism.gestionsapprovisionnement.entities.Commande;
import ism.gestionsapprovisionnement.repositories.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommandeServiceImpl implements CommandeService {

    @Autowired
     CommandeRepository commandeRepository;
    @Override
    public CommandeDto addCommande(CommandeDto commandeDto) {
        return null;
    }

    @Override
    public List<CommandeDto> getCommandesByClient(Long id) {
        Client client =new Client(id);
        List<Commande> commandes=commandeRepository.getByClient(client);
        return commandes
                .stream()
                .map(commande -> new CommandeDto(commande))
                .collect(Collectors.toList());
    }


}
