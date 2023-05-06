package com.application.ecommerce.repository;

import com.application.ecommerce.model.voucher.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherRepo extends JpaRepository<Voucher, Long> {

}
