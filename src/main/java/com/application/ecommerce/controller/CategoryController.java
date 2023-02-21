package com.application.ecommerce.controller;

import com.application.ecommerce.service.CategoryService;
import com.application.ecommerce.base.rest.CrudEndpoint;
import com.application.ecommerce.dto.CategoryCreateDTO;
import com.application.ecommerce.model.Category;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/category")
public class CategoryController extends CrudEndpoint<Category, Long, CategoryCreateDTO> {
    public CategoryController(CategoryService service) {
        super(service);
    }

    @Override
    protected void authorizeGetALLResource() {

    }

    @Override
    protected void authorizeQueryObject() {

    }

    @Override
    protected Category createObjectFromDTO(CategoryCreateDTO dto) {
        return null;
    }
}
