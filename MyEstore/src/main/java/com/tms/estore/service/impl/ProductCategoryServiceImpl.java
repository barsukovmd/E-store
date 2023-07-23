package com.tms.estore.service.impl;

import com.tms.estore.repository.ProductCategoryRepository;
import com.tms.estore.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    @Override
    public List<String> getProductCategories() {
        return productCategoryRepository.findAllCategory();
    }
}
