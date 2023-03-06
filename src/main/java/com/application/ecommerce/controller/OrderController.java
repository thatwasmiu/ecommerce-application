package com.application.ecommerce.controller;

import com.application.ecommerce.dto.CartCreateDTO;
import com.application.ecommerce.model.order.Order;
import com.application.ecommerce.service.OrderService;
import com.application.ecommerce.base.rest.CrudRestEndpoint;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/v1/cart")
@PreAuthorize("hasAuthority('CUSTOMER')")
public class OrderController extends CrudRestEndpoint<Order, Long, CartCreateDTO> {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    public OrderController(OrderService service) {
        super(service);
    }

    @GetMapping("list")
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public ResponseEntity<List<Order>> getAllOrder() {
        return new ResponseEntity<>(((OrderService) service).getOrderByOwner(), HttpStatus.OK);
    }

    @Override
    protected void authorizeGetALLResource() {

        if (authentication.getAuthorities()
                .stream()
                .anyMatch(r -> r.getAuthority().equals("ADMIN"))
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
