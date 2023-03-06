package com.application.ecommerce.service;

import com.application.ecommerce.config.AppUserDetails;
import com.application.ecommerce.model.user.User;
import com.application.ecommerce.base.rest.CrudService;
import com.application.ecommerce.model.order.Order;
import com.application.ecommerce.repository.OrderRepository;
import com.application.ecommerce.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService extends CrudService<Order, Long> {

    UserRepository userRepo;

    public OrderService(
            OrderRepository repo,
            AuthenticationService authenticationService,
            UserRepository userRepo
    ) {
        super(repo, authenticationService);
        this.userRepo = userRepo;
    }

    @Override
    protected void doAfterCreate(Order obj) {

    }

    @Override
    protected void doBeforeCreate(Order obj) {
        AppUserDetails user = (AppUserDetails) SecurityContextHolder
                        .getContext()
                        .getAuthentication().getPrincipal();

        obj.setOwner(user.getUser());
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

    public List<Order> getOrderByOwner() {
        AppUserDetails userDetails = (AppUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        return ((OrderRepository) repo).findByOwner(user);
    }
}
