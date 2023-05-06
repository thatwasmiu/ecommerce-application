package com.application.ecommerce.service;

import com.application.ecommerce.exception.ResourceNotFoundException;
import com.application.ecommerce.model.voucher.Voucher;
import com.application.ecommerce.repository.VoucherRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoucherService {
    private VoucherRepo repo;

    public VoucherService(VoucherRepo repo) {
        this.repo = repo;
    }

    public List<Voucher> getAll() {
        return repo.findAll();
    }

    public Voucher getVoucherById(Long id) {
        Optional<Voucher> voucher = repo.findById(id);
        if (voucher.isEmpty())
            throw new ResourceNotFoundException("Voucher Not Found");
        return voucher.get();
    }

    public void deleteVoucher(Long id) {
        repo.deleteById(id);
    }

    public Voucher upsertVoucher(Voucher voucher) {
        return repo.save(voucher);
    }

    public void importVoucher(List<Voucher> vouchers) {
        repo.saveAll(vouchers);
    }
}
