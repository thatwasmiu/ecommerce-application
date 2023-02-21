package com.application.ecommerce.service;

import com.application.ecommerce.model.User;
import com.application.ecommerce.base.rest.CrudService;
import com.application.ecommerce.model.Order;
import com.application.ecommerce.repository.OrderRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends CrudService<Order, Long> {

    UserService userService;

    public OrderService(
            OrderRepository repo,
            AuthenticationService authenticationService,
            UserService userService
    ) {
        super(repo, authenticationService);
        this.userService = userService;
    }

    @Override
    protected void doAfterCreate(Order obj) {

    }

    @Override
    protected void doBeforeCreate(Order obj) {
        User user = userService.getUserByUsername(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()
        );

        obj.setOwner(user);
    }

    @Override
    protected void doAfterDelete(Long aLong) {

    }

    @Override
    protected void doBeforeDelete(Order order) {
        if (order.getOwner().getUsername().equals(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()
        ))
            throw new RuntimeException();  // need custom exception
    }

    @Override
    protected String modifyQuery(String query) {
        StringBuilder sb = new StringBuilder();
//        sb.append("")
        return query;
    }
}
