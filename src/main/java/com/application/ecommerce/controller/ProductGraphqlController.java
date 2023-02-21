package com.application.ecommerce.controller;

import com.application.ecommerce.model.Product;
import com.application.ecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class ProductGraphqlController {
    ProductRepository repo;

// This thing does not be working man!!
    @QueryMapping
    public List<Product> queryProduct() {
        return repo.findAll();
    }
}
