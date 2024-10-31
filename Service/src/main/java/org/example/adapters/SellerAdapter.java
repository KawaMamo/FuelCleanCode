package org.example.adapters;

import org.example.contract.repository.SellerRepo;
import org.example.mappers.SellerMapper;
import org.example.model.Seller;
import org.example.repositories.SellerRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;
import java.util.Optional;

public class SellerAdapter implements SellerRepo {
    private final SellerRepository repository;
    private final SellerMapper mapper;

    public SellerAdapter(SellerRepository repository, SellerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Seller save(Seller seller) {
        final org.example.entities.Seller entity = mapper.domainToEntity(seller);
        entity.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        final org.example.entities.Seller save = repository.save(entity);
        return mapper.entityToDomain(save);
    }

    @Override
    public Optional<Seller> findById(Long id) {
        final org.example.entities.Seller seller = repository.findById(id).orElseThrow(NoSuchElementException::new);
        final Seller domain = mapper.entityToDomain(seller);
        return Optional.ofNullable(domain);
    }

    @Override
    public void delete(Seller seller) {
        repository.delete(mapper.domainToEntity(seller));
    }
}
