package com.tms.estore.controller;

import com.tms.estore.service.ProductCategoryService;
import com.tms.estore.service.SearchFacade;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import static com.tms.estore.utils.Constants.Attributes.PRODUCT_CATEGORIES;
import static com.tms.estore.utils.Constants.MappingPath.SEARCH_PATH;
import static com.tms.estore.utils.Constants.RequestParameters.*;

@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SearchFacade searchFacade;
    private final ProductCategoryService productCategoryService;

    @GetMapping("/search")
    public ModelAndView hasFilterPage(HttpSession session,
                                      @RequestParam(required = false) String result,
                                      @RequestParam(required = false) String filter,
                                      @PageableDefault(sort = ID) Pageable pageable,
                                      ModelAndView modelAndView) {
        searchFacade.processFilter(session, result, filter);
        modelAndView.addObject(PRODUCT_CATEGORIES, productCategoryService.getProductCategories());
        modelAndView.setViewName(SEARCH_PATH);
        searchFacade.setPagination(session, pageable, modelAndView);
        return modelAndView;
    }

    @PostMapping("/search-param")
    public ModelAndView showSearchPageByParam(HttpSession session,
                                              @RequestParam(name = SEARCH_CONDITION) String searchCondition) {
        session.removeAttribute(FILTER);
        return searchFacade.getProductsPageBySearchCondition(session, searchCondition);
    }

    @PostMapping("/search-filter")
    public ModelAndView showSearchPageByFilter(HttpServletRequest request,
                                               @RequestParam(required = false, name = SELECT) String type) {
        return searchFacade.getSearchFilterResultPagePath(request, type);
    }
}
