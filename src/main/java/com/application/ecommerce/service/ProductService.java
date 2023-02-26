package com.application.ecommerce.service;

import com.application.ecommerce.exception.ResourceNotFoundException;
import com.application.ecommerce.model.product.Product;
import com.application.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"products"})
public class ProductService {

    private final ProductRepository repo;

    @Cacheable
    public List<Product> getAllProduct() {
//        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        return repo.findAll();
    }

    public Product getProductById(Long id) {
        Optional<Product> product = repo.findById(id);
        if (product.isEmpty())
            throw new ResourceNotFoundException("Product Does Not Exit!");

        return product.get();
    }

    public Product upSertProduct(Product product) {
        return repo.save(product);
    }

    @CacheEvict(value = "products", allEntries=true)
    public void removeProductById(Long id) {
        repo.deleteById(id);
    }

}
