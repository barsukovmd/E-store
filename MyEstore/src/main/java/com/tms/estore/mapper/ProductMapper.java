package com.tms.estore.mapper;

import com.tms.estore.domain.Product;
import com.tms.estore.dto.ProductDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = CustomMapperConfig.class)
public interface ProductMapper {

    @Mapping(source = "product.productCategory.category", target = "category")
    ProductDto convertToProductDto(Product product);

    @InheritInverseConfiguration
    @Mapping(target = "productCategory", expression = "java(new ProductCategory(dto.getCategory()))")
    Product convertToProduct(ProductDto productDto);

    List<ProductDto> convertToProductDto(List<Product> products);
}
