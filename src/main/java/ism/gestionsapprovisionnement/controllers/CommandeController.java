package ism.gestionsapprovisionnement.controllers;

import ism.gestionsapprovisionnement.dto.ArticlePanierDto;
import ism.gestionsapprovisionnement.dto.PanierDto;
import ism.gestionsapprovisionnement.entities.*;
import ism.gestionsapprovisionnement.repositories.ArticleRepository;
import ism.gestionsapprovisionnement.repositories.ClientRepository;
import ism.gestionsapprovisionnement.repositories.CommandeRepository;
import ism.gestionsapprovisionnement.repositories.LigneCommandeRepository;
import ism.gestionsapprovisionnement.services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@SessionAttributes({"panier"})
public class CommandeController {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private  CommandeRepository commandeRepository;
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    LigneCommandeRepository ligneCommandeRepository;

    @Autowired
    private CommandeService service;
    @GetMapping("/liste-cmde-client")
    public String listerCommandeUnClient(
             Model model ,
             @RequestParam(name = "id",defaultValue = "") Long idClient,
             @RequestParam(name = "page",defaultValue = "0") int page,
             @RequestParam(name = "size",defaultValue = "8") int size
    ){

        Page<Commande> commandePage=commandeRepository.getByClient(new Client(idClient),PageRequest.of(page,size));
        model.addAttribute("listCommandes",commandePage.getContent());
        model.addAttribute("pages",new int[commandePage.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("idClient",idClient);
         return "commande" ;
    }

    @GetMapping("/liste-all-cmde")
    public String listerAllCommande(Model model,
                                    @RequestParam(name = "page",defaultValue = "0") int page,
                                    @RequestParam(name = "size",defaultValue = "8") int size){
        Page<Commande> commandePage=commandeRepository.findAll(PageRequest.of(page,size));
        model.addAttribute("listCommandes",commandePage.getContent());
        model.addAttribute("pages",new int[commandePage.getTotalPages()]);
        model.addAttribute("currentPage",page);
        return "commande" ;
    }

    ///

    @GetMapping("/form-add-cmde-client")
    public String loadFormCommande(Model model,
                                     @RequestParam(name = "id",defaultValue = "") Long idClient,
                                     @ModelAttribute("panier") PanierDto panier
                                   ){
     Optional<Client>  OpClient= clientRepository.findById(idClient);
       if(!OpClient.isPresent()){
           return "redirect:/liste-clients";
       }
       List<Article> articleList=articleRepository.findAll();
     //   model.addAttribute("client",OpClient.get());
        panier.setClient(OpClient.get());
        model.addAttribute("panier",panier);
        //Afficher les infos sur le Select
        model.addAttribute("articles",articleList);
        //Mapping du formulaire Ajout dans le Panier
        model.addAttribute("articlePanier",new ArticlePanierDto());

        return "form.commande";
    }

    @GetMapping ("/add-commande")
    public String saveCommande(Model model,
                                     @ModelAttribute("panier") PanierDto panier
    ){
        service.saveCommande(panier);
        //Vider le Panier
         model.addAttribute("panier",panier());
        return "redirect:/liste-cmde-client?id="+panier.getClient().getId();
    }

    @GetMapping("/form-add-payement-commande")
    public String loadFormPayement(Model model,
                                   @RequestParam(name = "id",defaultValue = "") Long idCommande){
       Optional<Commande>  OptCommande=commandeRepository.findById(idCommande);
       if(!OptCommande.isPresent()){
           return "redirect:/liste-all-cmde";
       }
       model.addAttribute("commande",OptCommande.get());
        return "form.etat.commande";
    }

    @GetMapping("/change-etat-commande")
    public String changeEtatCommande(Model model,
                                     @RequestParam(name="etat",defaultValue = "") int etat,
                                     @RequestParam(name="idCommande",defaultValue = "") Long idCommande){
        service.updateEtatCommande(idCommande,etat);
         return "redirect:/liste-all-cmde";
    }
    @ModelAttribute("panier")
    public PanierDto panier() {
        return new PanierDto(
                new ArrayList<>(),
                0,
                null
         );
    }


}
