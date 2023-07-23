package com.tms.estore.mapper;

import com.tms.estore.domain.Cart;
import com.tms.estore.dto.CartDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = CustomMapperConfig.class)
public interface CartMapper {

    @Mapping(source = "user", target = "userDto")
    @Mapping(source = "product", target = "productDto")
    @Mapping(source = "product.productCategory.category", target = "productDto.category")
    CartDto convertToCartDto(Cart cart);

    List<CartDto> convertToCartDto(List<Cart> carts);
}
