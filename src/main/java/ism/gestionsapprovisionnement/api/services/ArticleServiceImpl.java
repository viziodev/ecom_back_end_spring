package ism.gestionsapprovisionnement.api.services;

import ism.gestionsapprovisionnement.api.dto.ProductDetailDto;
import ism.gestionsapprovisionnement.api.dto.ProductDto;
import ism.gestionsapprovisionnement.entities.Article;
import ism.gestionsapprovisionnement.entities.Categorie;
import ism.gestionsapprovisionnement.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl  implements  ArticleService{
    @Autowired
    private ArticleRepository articleRepository;
    @Override
    public List<ProductDto> getCatalogue() {
        return articleRepository.findAll()
                  .stream()
                  .map(article -> new ProductDto(article))
                  .collect(Collectors.toList());
    }

    @Override
    public ProductDetailDto getDetailProduct(Long id) {
       Optional<Article> OptArticle= articleRepository.findById(id);
       if(OptArticle.isPresent()){
           Categorie categorie= OptArticle.get().getCategorie();
          List<ProductDto> productDtoList= categorie.getArticles()
                   .stream()
                   .filter(article -> article.getId()!=OptArticle.get().getId())
                   .map(article -> new ProductDto(article))
                   .collect(Collectors.toList());
           return new ProductDetailDto(new ProductDto(OptArticle.get()),productDtoList);
       }
        return null;
    }
}
