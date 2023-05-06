package com.application.ecommerce.controller;

import com.application.ecommerce.model.product.Product;
import com.application.ecommerce.repository.ProductRepository;
import com.application.ecommerce.service.ProductService;
import com.application.ecommerce.base.rsql.AppRsqlVisitor;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;

import lombok.AllArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
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
@RequestMapping("/api/v1/products")
@AllArgsConstructor
@CrossOrigin
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
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        Product newProduct = service.upSertProduct(product);

        URI path = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newProduct.getId())
                .toUri();
        return ResponseEntity.created(path).build();
    }

    @PostMapping("/import")
    public void importFromJson(@RequestBody List<Product> products) {
        service.saveAllProduct(products);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> deleteProductById(@PathVariable Long id) {
        service.removeProductById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/all")
    public void truncateProductRecord() {
        service.truncateProductTable();
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        return new ResponseEntity<>(service.upSertProduct(product), HttpStatus.OK);
    }

    @GetMapping("/search")
    public List<Product> findAllByRsql(@RequestParam(value = "query") String search) {
        Node rootNode = new RSQLParser().parse(search);
        Specification<Product> spec = rootNode.accept(new AppRsqlVisitor<>());
        return dao.findAll(spec);
    }

    @GetMapping("/records")
    public List<Product> getRecord() {

        return service.findRecords(false);
    }

}
