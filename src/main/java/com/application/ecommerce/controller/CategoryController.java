package com.application.ecommerce.controller;

import com.application.ecommerce.service.CategoryService;
import com.application.ecommerce.base.rest.CrudRestEndpoint;
import com.application.ecommerce.dto.CategoryCreateDTO;
import com.application.ecommerce.model.category.Category;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/category")
public class CategoryController extends CrudRestEndpoint<Category, Long, CategoryCreateDTO> {

    private final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    public CategoryController(CategoryService service) {
        super(service);
    }

    @Override
    protected void authorizeGetALLResource() {
        if (authentication.getAuthorities()
                .stream()
                .anyMatch(r -> r.getAuthority().equals("CUSTOMER"))
        )
            throw new RuntimeException();
    }

    @Override
    protected void authorizeQueryObject() {

    }

    @Override
    protected Category createObjectFromDTO(CategoryCreateDTO dto) {
        return null;
    }
}
