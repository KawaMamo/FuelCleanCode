package org.example.contract.repository;

import org.example.model.PriceCategory;

import java.util.Optional;

public interface PriceCategoryRepo {
    PriceCategory save(PriceCategory priceCategory);
    Optional<PriceCategory> findById(Long id);
    void delete(PriceCategory priceCategory);
}
