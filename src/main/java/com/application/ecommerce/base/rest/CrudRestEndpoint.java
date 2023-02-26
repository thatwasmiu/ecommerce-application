package com.application.ecommerce.base.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;


public abstract class CrudRestEndpoint<T extends AbstractEntity, ID, O extends RequestDTO> {

    protected final CrudService<T, ID> service;

    public CrudRestEndpoint(CrudService<T, ID> service) {
        this.service = service;
    }

    @GetMapping
    public List<T> getAllResource() {
        this.authorizeGetALLResource();
        return service.getAll();
    }

    protected abstract void authorizeGetALLResource();

    @GetMapping("/search")
    public List<T> queryObject(@RequestParam(value = "query") String query) {
        this.authorizeQueryObject();
        return service.search(query);
    }

    protected abstract void authorizeQueryObject();

    @PostMapping
    public ResponseEntity<Objects> createObject(@RequestBody O dto) {
        T obj = this.createObjectFromDTO(dto);
        service.upsert(obj);
        URI path = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(path).build();
    }

    protected abstract T createObjectFromDTO(O dto);

    @DeleteMapping("/{id}")
    public  ResponseEntity<Objects> deleteObject(@PathVariable("id") ID id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
