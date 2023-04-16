package ism.gestionsapprovisionnement.api.services;

import ism.gestionsapprovisionnement.api.dto.ClientDto;
import ism.gestionsapprovisionnement.entities.Client;
import ism.gestionsapprovisionnement.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService{
    @Autowired
    ClientRepository clientRepository;
    @Override
    public ClientDto getClientByLogin(String login) {
      Client client =(Client) clientRepository.findByUsername(login);
           return new ClientDto(client);
    }

    @Override
    public void addClient(ClientDto client) {

    }
}
