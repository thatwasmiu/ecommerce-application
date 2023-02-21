package com.application.ecommerce.service;

import com.application.ecommerce.exception.ResourceNotFoundException;
import com.application.ecommerce.model.Product;
import com.application.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repo;

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

    public void removeProductById(Long id) {
        repo.deleteById(id);
    }

}
