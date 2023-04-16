package ism.gestionsapprovisionnement.api.dto;

import lombok.*;

import java.util.List;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailDto {
    ProductDto product;
    List<ProductDto> products;
}
