package org.example.adapters;

import org.example.contract.repository.PriceCategoryRepo;
import org.example.mappers.PriceCategoryMapper;
import org.example.model.PriceCategory;
import org.example.repositories.PriceCategoryRepository;
import org.example.entities.PriceCategoryEntity;

import java.time.LocalDateTime;
import java.util.Optional;

public class PriceCategoryAdapter implements PriceCategoryRepo {

    private final PriceCategoryRepository repository;
    private final PriceCategoryMapper mapper;

    public PriceCategoryAdapter(PriceCategoryRepository repository, PriceCategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public PriceCategory save(PriceCategory priceCategory) {
        final PriceCategoryEntity priceCategoryEntity = mapper.domainToEntity(priceCategory);
        priceCategoryEntity.setCreatedAt(LocalDateTime.now());
        final PriceCategoryEntity save = repository.save(priceCategoryEntity);
        return mapper.entityToDomain(save);
    }

    @Override
    public Optional<PriceCategory> findById(Long id) {
        final Optional<PriceCategoryEntity> byId = repository.findById(id);
        final PriceCategoryEntity priceCategoryEntity = byId.orElse(null);
        final PriceCategory priceCategory = mapper.entityToDomain(priceCategoryEntity);
        return Optional.ofNullable(priceCategory);
    }
}
