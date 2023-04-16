package ism.gestionsapprovisionnement.api.controller;

import ism.gestionsapprovisionnement.api.dto.ProductDetailDto;
import ism.gestionsapprovisionnement.api.dto.ProductDto;
import ism.gestionsapprovisionnement.api.exceptions.NoDataException;
import ism.gestionsapprovisionnement.api.exceptions.ResourceNotFoundException;
import ism.gestionsapprovisionnement.api.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
//@CrossOrigin("http://localhost:4200")
//Request http/:localhost:8080/api/articles
public class ArticleRestController {
    @Autowired
    private ArticleService articleService;
    //Request GET  http/:localhost:8080/api/articles
    //Request POST  http/:localhost:8080/api/articles
    //uri => api/articles
    @GetMapping("")
    @ResponseBody
    public ResponseEntity<List<ProductDto>>  getCatalogue(){
        List<ProductDto> body=articleService.getCatalogue();
        if(body.isEmpty())
            throw new NoDataException("Pas d'articles disponible");
           return  new ResponseEntity<>(body, HttpStatus.OK);//200
    }

    //Request GET  http/:localhost:8080/api/articles/1
    //uri => api/articles/1
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ProductDetailDto>  getArticleById(@PathVariable("id") Long id){
        ProductDetailDto body=articleService.getDetailProduct(id);
        if(body==null) throw new ResourceNotFoundException(
                String.format("Pas d'article ayant l'identifiant %d    ",id));
        return  new ResponseEntity<>(body, HttpStatus.OK);//200
    }

}
