package com.application.ecommerce.base.rest;

import com.application.ecommerce.base.rsql.AppRsqlVisitor;
import com.application.ecommerce.service.AuthenticationService;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;


@Transactional
public abstract class CrudService<T extends AbstractEntity, ID> {
    AuthenticationService authenticationService;
    protected final ResourceRepository<T, ID> repo;

    public CrudService(
            ResourceRepository<T, ID> repo,
            AuthenticationService authenticationService)
    {
        this.repo = repo;
        this.authenticationService = authenticationService;
    }

    public List<T> getAll() {
        return repo.findAll();
    }

    public T upsert(T obj) {
        this.doBeforeCreate(obj);
        T newObj = repo.save(obj);
        this.doAfterCreate(obj);
        return newObj;
    }

    protected abstract void doAfterCreate(T obj);
    protected abstract void doBeforeCreate(T obj);

    public ResponseEntity<Objects> delete(ID id) {
        T obj = repo.getReferenceById(id);
        this.doBeforeDelete(obj);
        repo.deleteById(id);
        this.doAfterDelete(id);
        return ResponseEntity.ok().build();
    }

    protected abstract void doAfterDelete(ID id);
    protected abstract void doBeforeDelete(T obj);

    public List<T> search(String query) {
        query = modifyQuery(query);
        Node rootNode = new RSQLParser().parse(query);
        Specification<T> spec = rootNode.accept(new AppRsqlVisitor<>());
        return repo.findAll(spec);
    }

    protected abstract String modifyQuery(String query);
}
