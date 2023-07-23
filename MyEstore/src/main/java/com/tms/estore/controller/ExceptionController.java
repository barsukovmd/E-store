package com.tms.estore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import static com.tms.estore.utils.Constants.MappingPath.*;

@RestController
public class ExceptionController {

    @GetMapping("/error-500")
    public ModelAndView showError500Page() {
        return new ModelAndView(ERROR_500);
    }

    @GetMapping("/some-error")
    public ModelAndView showSomeErrorPage() {
        return new ModelAndView(SOME_ERROR);
    }

    @GetMapping("/error-403")
    public ModelAndView showError403Page() {
        return new ModelAndView(ERROR_403);
    }
}
