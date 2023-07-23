package com.tms.estore.service;

import com.tms.estore.dto.CartDto;
import com.tms.estore.dto.ProductDto;
import com.tms.estore.model.Location;

import java.util.List;
import java.util.Map;

public interface CartService {

    void addSelectedProduct(Long userId, Long productId, Location location);

    List<CartDto> getSelectedProducts(Long userId, Location location);

    void deleteProduct(Long userId, Long productId, Location location);

    void deleteCartProductsAfterBuy(Long userId);

    List<ProductDto> getPurchasedProducts(Long userId, Location location);

    List<Map<Long, Long>> getMostFavorite();
}
