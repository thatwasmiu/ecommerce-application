package com.application.ecommerce.controller;

import com.application.ecommerce.service.CategoryService;
import com.application.ecommerce.base.rest.CrudRestEndpoint;
import com.application.ecommerce.dto.CategoryCreateDTO;
import com.application.ecommerce.model.category.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
@PreAuthorize("hasAnyAuthority('ADMIN')")

public class CategoryController extends CrudRestEndpoint<Category, Long, CategoryCreateDTO> {

    public CategoryController(CategoryService service) {
        super(service);
    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public ResponseEntity<List<String>> getCategory() {
        return new ResponseEntity<>(((CategoryService) service).getAllName(), HttpStatus.OK);
    }


    // Ignore
    @Override
    protected void authorizeGetALLResource() {

    }

    @Override
    protected void authorizeQueryObject() {

    }

    @Override
    protected Category createObjectFromDTO(CategoryCreateDTO dto) {
        return dto.createCategory();
    }
}
