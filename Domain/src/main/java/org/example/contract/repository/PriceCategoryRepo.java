package org.example.contract.repository;

import org.example.model.PriceCategory;

public interface PriceCategoryRepo {
    PriceCategory save(PriceCategory priceCategory);
}
