package com.tms.estore.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@Builder
public class ProductDto {

    private Long id;
    private String name;
    private BigDecimal price;
    private String category;
    private String info;
}
