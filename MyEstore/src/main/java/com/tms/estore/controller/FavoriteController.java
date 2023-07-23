package com.tms.estore.controller;

import com.tms.estore.dto.ProductDto;
import com.tms.estore.model.Location;
import com.tms.estore.service.CartService;
import com.tms.estore.service.ShopFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.tms.estore.utils.Constants.Attributes.FAVORITE_PRODUCTS;
import static com.tms.estore.utils.Constants.MappingPath.FAVORITES;
import static com.tms.estore.utils.Constants.MappingPath.REDIRECT_TO_FAVORITES;
import static com.tms.estore.utils.Constants.PAGE;
import static com.tms.estore.utils.Constants.RequestParameters.ID;
import static com.tms.estore.utils.Constants.RequestParameters.LOCATION;
import static com.tms.estore.utils.ControllerUtils.getAuthenticationUserId;

@RestController
@RequiredArgsConstructor
public class FavoriteController {

    private final CartService cartService;
    private final ShopFacade shopFacade;

    @GetMapping("/favorites")
    public ModelAndView showFavoritesPage() {
        List<ProductDto> products = shopFacade.getFavoriteProducts(getAuthenticationUserId());
        ModelMap modelMap = new ModelMap(FAVORITE_PRODUCTS, products);
        return new ModelAndView(FAVORITES, modelMap);
    }

    @GetMapping("/add-favorite")
    public ModelAndView addProductToFavorite(@RequestParam(name = ID) Long productId,
                                             @RequestParam(name = LOCATION) String location,
                                             @RequestParam(name = PAGE, required = false) Integer page) {
        cartService.addSelectedProduct(getAuthenticationUserId(), productId, Location.FAVORITE);
        return shopFacade.getModelAndViewByParams(productId, location, page);
    }

    @GetMapping("/delete-favorite")
    public ModelAndView deleteProductFromFavorite(@RequestParam(name = ID) Long productId) {
        cartService.deleteProduct(getAuthenticationUserId(), productId, Location.FAVORITE);
        return new ModelAndView(REDIRECT_TO_FAVORITES);
    }
}
