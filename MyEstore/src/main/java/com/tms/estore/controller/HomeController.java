package com.tms.estore.controller;

import com.tms.estore.service.ShopFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final ShopFacade shopFacade;

    @GetMapping(value = {"/", "/estore"})
    public ModelAndView redirectToEshopPage(ModelAndView modelAndView) {
        shopFacade.getEshopView(modelAndView);
        return modelAndView;
    }

    @PostMapping(value = {"/", "/estore"})
    public ModelAndView entersToEshop(ModelAndView modelAndView) {
        shopFacade.getEshopView(modelAndView);
        return modelAndView;
    }
}
