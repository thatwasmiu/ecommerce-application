package com.application.ecommerce.controller;

import com.application.ecommerce.dto.CartCreateDTO;
import com.application.ecommerce.model.order.Order;
import com.application.ecommerce.service.OrderService;
import com.application.ecommerce.base.rest.CrudRestEndpoint;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/cart")
public class OrderController extends CrudRestEndpoint<Order, Long, CartCreateDTO> {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    public OrderController(OrderService service) {
        super(service);
    }

    @Override
    protected void authorizeGetALLResource() {

        if (authentication.getAuthorities()
                .stream()
                .anyMatch(r -> r.getAuthority().equals("CUSTOMER"))
        )
            throw new RuntimeException();
    }

    @Override
    protected void authorizeQueryObject() {

    }

    @Override
    protected Order createObjectFromDTO(CartCreateDTO dto) {
        return CartCreateDTO.createCartFromDTO(dto);
    }
}
