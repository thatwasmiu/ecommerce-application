package com.application.ecommerce.service;

import com.application.ecommerce.base.rest.CrudService;
import com.application.ecommerce.model.ProductPurchase;
import com.application.ecommerce.repository.ProductPurchaseRepository;

public class ProductPurchaseService extends CrudService<ProductPurchase, Long> {
    public ProductPurchaseService(
            ProductPurchaseRepository repo,
            AuthenticationService authenticationService)
    {
        super(repo, authenticationService);
    }

    @Override
    protected void doAfterCreate(ProductPurchase obj) {

    }

    @Override
    protected void doBeforeCreate(ProductPurchase obj) {

    }

    @Override
    protected void doAfterDelete(Long aLong) {

    }

    @Override
    protected void doBeforeDelete(ProductPurchase productPurchase) {

    }

    @Override
    protected String modifyQuery(String query) {
        return null;
    }
}
