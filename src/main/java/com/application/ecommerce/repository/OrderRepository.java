package com.application.ecommerce.repository;

import com.application.ecommerce.base.rest.ResourceRepository;
import com.application.ecommerce.model.order.Order;
import com.application.ecommerce.model.user.User;

import java.util.List;

public interface OrderRepository extends ResourceRepository<Order, Long> {
    List<Order> findByOwner(User owner);
}
