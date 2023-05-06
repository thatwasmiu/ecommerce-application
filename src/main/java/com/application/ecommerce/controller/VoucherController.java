package com.application.ecommerce.controller;

import com.application.ecommerce.model.product.Product;
import com.application.ecommerce.model.voucher.Voucher;
import com.application.ecommerce.service.VoucherService;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/vouchers")
@CrossOrigin
public class VoucherController {
    private VoucherService service;

    public VoucherController(VoucherService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Voucher>> retrieveAllVoucher() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voucher> retrieveVoucherById(@PathVariable Long id) {
        Voucher voucher = service.getVoucherById(id);
        return new ResponseEntity<>(voucher, HttpStatus.CREATED);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Voucher> createVoucher(@Valid @RequestBody Voucher voucher) {
        service.upsertVoucher(voucher);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/import")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void importFromJson(@RequestBody List<Voucher> vouchers) {
        service.importVoucher(vouchers);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Voucher> updateProduct(@RequestBody Voucher voucher) {
        return new ResponseEntity<>(service.upsertVoucher(voucher), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProductById(@PathVariable Long id) {
        service.deleteVoucher(id);
        return ResponseEntity.ok().build();
    }
}
