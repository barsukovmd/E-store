package com.tms.estore.controller;

import com.tms.estore.dto.CartDto;
import com.tms.estore.model.Location;
import com.tms.estore.service.CartService;
import com.tms.estore.service.ShopFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.tms.estore.utils.Constants.Attributes.CART_PRODUCTS;
import static com.tms.estore.utils.Constants.Attributes.FULL_PRICE;
import static com.tms.estore.utils.Constants.MappingPath.REDIRECT_TO_CART;
import static com.tms.estore.utils.Constants.MappingPath.SHOPPING_CART;
import static com.tms.estore.utils.Constants.PAGE;
import static com.tms.estore.utils.Constants.RequestParameters.*;
import static com.tms.estore.utils.ControllerUtils.getAuthenticationUserId;
import static com.tms.estore.utils.ControllerUtils.getProductsPrice;

@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final ShopFacade shopFacade;

    @GetMapping("/cart")
    public ModelAndView showCardPage(ModelAndView modelAndView) {
        List<CartDto> cartProducts = cartService.getSelectedProducts(getAuthenticationUserId(), Location.CART);
        modelAndView.addObject(CART_PRODUCTS, cartProducts);
        modelAndView.addObject(FULL_PRICE, getProductsPrice(cartProducts));
        modelAndView.setViewName(SHOPPING_CART);
        return modelAndView;
    }

    @PostMapping("/cart-processing")
    public ModelAndView showCartProcessingPage(@RequestParam String buy) {
        return shopFacade.getPageByParam(buy);
    }

    @GetMapping("/add-cart")
    public ModelAndView addProductToCart(@RequestParam(name = ID) Long productId,
                                         @RequestParam(name = SHOP) String shopFlag,
                                         @RequestParam(name = LOCATION) String location,
                                         @RequestParam(name = PAGE, required = false) Integer page) {
        cartService.addSelectedProduct(getAuthenticationUserId(), productId, Location.CART);
        return new ModelAndView(shopFacade.getPathFromAddCartByParameters(productId, shopFlag, location, page));
    }

    @GetMapping("/delete-cart")
    public ModelAndView deleteProductFromCart(@RequestParam(name = ID) Long productId) {
        cartService.deleteProduct(getAuthenticationUserId(), productId, Location.CART);
        return new ModelAndView(REDIRECT_TO_CART);
    }
}
