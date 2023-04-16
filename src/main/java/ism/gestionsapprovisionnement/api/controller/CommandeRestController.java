package ism.gestionsapprovisionnement.api.controller;

import ism.gestionsapprovisionnement.api.dto.CommandeDto;
import ism.gestionsapprovisionnement.api.dto.ProductDto;
import ism.gestionsapprovisionnement.api.services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commandes")
//@CrossOrigin("http://localhost:4200")
public class CommandeRestController {
    @Autowired
    CommandeService commandeService;
    @PostMapping("")
    @ResponseBody
    public ResponseEntity<CommandeDto> addCommande(@RequestBody CommandeDto commandeDto){
        CommandeDto commande=commandeService.addCommande(commandeDto);
        return  new ResponseEntity<>(commande, HttpStatus.CREATED);//201
    }

    @GetMapping("/client/{id}")
    @ResponseBody
    public ResponseEntity<List<CommandeDto>> getCommandesByClient(@PathVariable("id") Long id) {
        List<CommandeDto> commandes = commandeService.getCommandesByClient(id);
        //System.out.println(commandes.size());
        return new ResponseEntity<>(commandes, HttpStatus.OK);//201
    }
    }
