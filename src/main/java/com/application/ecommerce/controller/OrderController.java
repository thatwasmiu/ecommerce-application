package com.application.ecommerce.controller;

import com.application.ecommerce.dto.OrderCreateDTO;
import com.application.ecommerce.model.order.Order;
import com.application.ecommerce.repository.ProductPurchaseRepo;
import com.application.ecommerce.service.OrderService;
import com.application.ecommerce.base.rest.CrudRestEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/orders")
@CrossOrigin
public class OrderController extends CrudRestEndpoint<Order, Long, OrderCreateDTO> {

    @Autowired //heresy I know
    private ProductPurchaseRepo productPurchaseRepo;

    public OrderController(OrderService service) {
        super(service);
    }

    @GetMapping("list")
//    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'ADMIN')")
    public ResponseEntity<List<Order>> getAllOrder() {
        return new ResponseEntity<>(((OrderService) service).getOrderByOwner(), HttpStatus.OK);
    }

    @PutMapping
    public void updateOrder(@RequestBody Order order) {
        service.upsert(order);
    }

    @Override
    protected void authorizeGetALLResource() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getAuthorities()
                .stream()
                .anyMatch(r -> r.getAuthority().equals("ADMIN"))
        )
            throw new RuntimeException("Not Authorized!!");
    }

    @Override
    protected void authorizeQueryObject() {

    }

    @Override
    protected Order createObjectFromDTO(OrderCreateDTO dto) {
        Order order = OrderCreateDTO.createOrderFromDTO(dto);
        order.setProductPurchases(productPurchaseRepo.saveAll(dto.getProducts()));
        return order;
    }
}
