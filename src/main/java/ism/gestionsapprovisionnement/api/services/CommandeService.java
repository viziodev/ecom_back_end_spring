package ism.gestionsapprovisionnement.api.services;

import ism.gestionsapprovisionnement.api.dto.CommandeDto;

import java.util.List;

public interface CommandeService {
    CommandeDto addCommande(CommandeDto commandeDto);
    List<CommandeDto> getCommandesByClient(Long id);
}
