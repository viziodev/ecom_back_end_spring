package ism.gestionsapprovisionnement.api.services;

import ism.gestionsapprovisionnement.api.dto.ProductDetailDto;
import ism.gestionsapprovisionnement.api.dto.ProductDto;

import java.util.List;

public interface ArticleService {
    List<ProductDto> getCatalogue();
    ProductDetailDto getDetailProduct(Long id);
}
