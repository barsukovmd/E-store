package com.tms.estore.controller;

import com.tms.estore.dto.UserFormDto;
import com.tms.estore.service.ShopFacade;
import com.tms.estore.validator.EditValidator;
import com.tms.estore.validator.UserValidator;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import static com.tms.estore.utils.Constants.Attributes.USER;
import static com.tms.estore.utils.Constants.MappingPath.*;
import static com.tms.estore.utils.ControllerUtils.getAuthenticationUserId;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserValidator userValidator;
    private final ShopFacade shopFacade;

    @GetMapping("/login")
    public ModelAndView showLoginPage(ModelAndView modelAndView) {
        modelAndView.setViewName(LOGIN);
        return modelAndView;
    }

    @GetMapping("/create-user")
    public ModelAndView create(@ModelAttribute(USER) UserFormDto user) {
        return new ModelAndView(CREATE_USER);
    }

    @PostMapping("/create-user")
    public ModelAndView createUser(@Validated({Default.class, EditValidator.class}) @ModelAttribute(USER) UserFormDto user,
                                   BindingResult bindingResult,
                                   ModelAndView modelAndView) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(CREATE_USER);
        } else {
            shopFacade.createUser(user);
            modelAndView.setViewName(SUCCESS_REGISTER);
        }
        return modelAndView;
    }

    @GetMapping("/edit-user")
    public ModelAndView edit() {
        return shopFacade.getUserEditForm(getAuthenticationUserId());
    }

    @PostMapping("/edit-user")
    public ModelAndView editUser(@Validated(EditValidator.class) @ModelAttribute(USER) UserFormDto user,
                                 BindingResult bindingResult,
                                 ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(EDIT);
        } else {
            shopFacade.editUser(user);
            modelAndView.setViewName(REDIRECT_TO_ACCOUNT);
        }
        return modelAndView;
    }
}
