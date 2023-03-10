package com.application.ecommerce.controller;

import com.application.ecommerce.model.product.Product;
import com.application.ecommerce.repository.ProductRepository;
import com.application.ecommerce.service.ProductService;
import com.application.ecommerce.base.rsql.AppRsqlVisitor;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import jakarta.validation.Valid;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("api/v1/products")
@AllArgsConstructor
public class ProductController {

    ProductService service;

    ProductRepository dao;

    @GetMapping
    public ResponseEntity<List<Product>> retrieveAllProduct() {
        return new ResponseEntity<>(service.getAllProduct(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Product>> retrieveProductById(@PathVariable Long id) {

        Product product = service.getProductById(id);

        EntityModel<Product> entityModel = EntityModel.of(product);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllProduct());
        entityModel.add(link.withRel("all-products"));
        return new ResponseEntity<>(entityModel, HttpStatus.CREATED);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        Product newProduct = service.upSertProduct(product);

        URI path = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newProduct.getId())
                .toUri();
        return ResponseEntity.created(path).build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id) {
        service.removeProductById(id);
        return new ResponseEntity<>("Successfully Delete Product!", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        return new ResponseEntity<>(service.upSertProduct(product), HttpStatus.OK);
    }

    @GetMapping("/search")
    public List<Product> findAllByRsql(@RequestParam(value = "query") String search) {
        Node rootNode = new RSQLParser().parse(search);
        Specification<Product> spec = rootNode.accept(new AppRsqlVisitor<>());
        return dao.findAll(spec);
    }

//    @PostMapping("/excel")
//    public ResponseEntity<Object> inputExcelFile(MultipartFile multipartFile) {
//
//    }

}
