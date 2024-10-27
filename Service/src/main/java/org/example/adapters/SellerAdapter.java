package org.example.adapters;

import org.example.contract.repository.SellerRepo;
import org.example.model.Seller;

import java.util.Optional;

public class SellerAdapter implements SellerRepo {
    @Override
    public Seller save(Seller seller) {
        return null;
    }

    @Override
    public Optional<Seller> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Seller seller) {

    }
}
