package ism.gestionsapprovisionnement.api.services;

import ism.gestionsapprovisionnement.api.dto.ClientDto;
import ism.gestionsapprovisionnement.entities.Client;
import ism.gestionsapprovisionnement.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceRestImpl implements SecurityService{
    @Autowired
    ClientRepository clientRepository;
    @Override
    public Client getClientByLogin(String login) {
          Client client =(Client) clientRepository.findByUsername(login);
             return client;
    }

    @Override
    public void addClient(ClientDto client) {

    }
}
