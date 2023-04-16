package ism.gestionsapprovisionnement.api.services;

import ism.gestionsapprovisionnement.api.dto.ClientDto;
import ism.gestionsapprovisionnement.entities.Client;

public interface SecurityService {
    Client getClientByLogin(String login);
    void addClient(ClientDto client);
}
