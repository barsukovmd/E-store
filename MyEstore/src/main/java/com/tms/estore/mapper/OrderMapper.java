package com.tms.estore.mapper;

import com.tms.estore.domain.Order;
import com.tms.estore.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CustomMapperConfig.class, uses = ProductMapper.class)
public interface OrderMapper {

    @Mapping(source = "user", target = "userDto")
    @Mapping(source = "products", target = "productDto")
    OrderDto convertToOrderDto(Order order);
}
