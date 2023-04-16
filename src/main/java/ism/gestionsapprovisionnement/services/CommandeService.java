package ism.gestionsapprovisionnement.services;

import ism.gestionsapprovisionnement.dto.PanierDto;
import ism.gestionsapprovisionnement.entities.Article;
import ism.gestionsapprovisionnement.entities.Commande;
import ism.gestionsapprovisionnement.entities.EtatCommande;
import ism.gestionsapprovisionnement.entities.LigneCommande;
import ism.gestionsapprovisionnement.repositories.CommandeRepository;
import ism.gestionsapprovisionnement.repositories.LigneCommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CommandeService {
    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    LigneCommandeRepository ligneCommandeRepository;
    public void saveCommande(PanierDto panier){
        Commande commande=new Commande(null
                ,new Date(),
                panier.getTotal(),
                EtatCommande.Encours,panier.getClient(),
                panier.getClient().getAdresse());
        commandeRepository.save(commande);
        panier.getArticles().stream().forEach(articlePanier->{
            ligneCommandeRepository.save(
                    new LigneCommande(
                            null,articlePanier.getPrix(),
                            articlePanier.getQuantite()
                            ,articlePanier.getMontant(),
                            commande,
                            new Article(articlePanier.getIdArticle())));
        });
    }

    public void  updateEtatCommande(Long idCommande,int etat){
        Optional<Commande> OptCommande=commandeRepository.findById(idCommande);
        if(OptCommande.isPresent()){
            Commande cmde= OptCommande.get();
            cmde.setEtat(EtatCommande.toEnum(etat));
            commandeRepository.save(cmde);
        }
    }
}
