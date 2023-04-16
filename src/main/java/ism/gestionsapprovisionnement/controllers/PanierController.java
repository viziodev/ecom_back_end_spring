package ism.gestionsapprovisionnement.controllers;

import ism.gestionsapprovisionnement.dto.ArticlePanierDto;
import ism.gestionsapprovisionnement.dto.PanierDto;
import ism.gestionsapprovisionnement.entities.Article;
import ism.gestionsapprovisionnement.entities.Client;
import ism.gestionsapprovisionnement.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes({"panier"})
public class PanierController {
    @Autowired
    private ArticleRepository articleRepository;
    @PostMapping("/add-panier")
    public String addArticleToPanier(Model model,
                                     ArticlePanierDto articlePanier,
                                     @ModelAttribute("panier") PanierDto panier
    ){
        Article article=articleRepository.findById(articlePanier.getIdArticle()).get();
        articlePanier.setLibelle(article.getLibelle());
       // panier.getArticles().add(articlePanier);
        panier.addArticleToPanier(articlePanier);
        panier.calculMontant(articlePanier.getMontant());
        model.addAttribute("panier",panier);
        return "redirect:/form-add-cmde-client?id="+panier.getClient().getId();
    }

}
