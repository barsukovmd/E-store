package com.tms.estore.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
public class OrderDto {

    private Long id;
    private String name;
    private LocalDate date;
    private UserDto userDto;
    private List<ProductDto> productDto;

    public List<ProductDto> getProductDto() {
        return productDto == null ? new ArrayList<>() : productDto;
    }
}
