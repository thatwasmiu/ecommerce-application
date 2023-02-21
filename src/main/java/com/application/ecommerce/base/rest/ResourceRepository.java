package com.application.ecommerce.base.rest;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Transactional
public interface ResourceRepository<T extends AbstractEntity, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
}
