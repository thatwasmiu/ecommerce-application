package com.application.ecommerce.service;

import com.application.ecommerce.repository.CategoryRepo;
import com.application.ecommerce.base.rest.CrudService;
import com.application.ecommerce.model.category.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends CrudService<Category, Long> {


    public CategoryService(CategoryRepo repo, AuthenticationService authenticationService) {
        super(repo, authenticationService);
    }

    @Override
    protected void doAfterCreate(Category obj) {

    }

    @Override
    protected void doBeforeCreate(Category obj) {

    }

    @Override
    protected void doAfterDelete(Long aLong) {

    }

    @Override
    protected void doBeforeDelete(Category obj) {

    }

    @Override
    protected String modifyQuery(String query) {
        return null;
    }
}
