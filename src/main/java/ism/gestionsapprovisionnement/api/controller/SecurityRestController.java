package ism.gestionsapprovisionnement.api.controller;

import ism.gestionsapprovisionnement.api.dto.ClientDto;
import ism.gestionsapprovisionnement.api.dto.CommandeDto;
import ism.gestionsapprovisionnement.api.exceptions.ResourceNotFoundException;
import ism.gestionsapprovisionnement.entities.Client;

import ism.gestionsapprovisionnement.api.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/security")
public class SecurityRestController {

    @Autowired
    SecurityService service;
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<ClientDto> connection(@RequestBody UserConnectDto userConnect){
       Client client=service.getClientByLogin(userConnect.login);
        if(client==null) throw new ResourceNotFoundException(
               "Login ou Mot de Passe Incorrect");
        return  new ResponseEntity<>(new ClientDto(client), HttpStatus.OK);
    }
}
class UserConnectDto{
     public String   login;
     public String   password;
}
