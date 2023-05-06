package com.application.ecommerce.service;

import com.application.ecommerce.exception.ResourceNotFoundException;
import com.application.ecommerce.model.product.Product;
import com.application.ecommerce.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "products")
public class ProductService {

    private final ProductRepository repo;
    private final EntityManager entityManager;

    @Cacheable
    public List<Product> getAllProduct() {
//        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        return repo.findAll();
    }

    @CacheEvict(value = "products", allEntries = true)
    public Product getProductById(Long id) {
        Optional<Product> product = repo.findById(id);
        if (product.isEmpty())
            throw new ResourceNotFoundException("Product Does Not Exit!");

        return product.get();
    }

    @CacheEvict(value = "products", allEntries = true)
    public void saveAllProduct(List<Product> products) {
        repo.saveAll(products);
    }

    @CacheEvict(value = "products", allEntries = true)
    public Product upSertProduct(Product product) {
        return repo.save(product);
    }

    @CacheEvict(value = "products", allEntries = true)
    public void removeProductById(Long id) {
        repo.deleteById(id);
    }

    @CacheEvict(value = "products", allEntries = true)
    public List<Product> findRecords(boolean isDeleted) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedProductFilter");
        filter.setParameter("isDeleted", isDeleted);
        List<Product> products = repo.findAll();
        session.disableFilter("deletedProductFilter");

        return products;
    }

    @CacheEvict(value = "products", allEntries = true)
    public void truncateProductTable() {
        repo.truncateTable();
    }
}
