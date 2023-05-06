package com.application.ecommerce.service;

import com.application.ecommerce.model.product.Product;
import com.application.ecommerce.repository.CategoryRepo;
import com.application.ecommerce.base.rest.CrudService;
import com.application.ecommerce.model.category.Category;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService extends CrudService<Category, Long> {

    public List<String> getAllName() {
        List<Category> categories = ((CategoryRepo) repo).findAll();
        return categories.stream().map(category -> category.getType().toString()).toList();
    }

    public CategoryService(CategoryRepo repo, AuthenticationService authenticationService) {
        super(repo, authenticationService);
    }


    public void saveAllCategory(List<Category> categories) {
        repo.saveAll(categories);
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
