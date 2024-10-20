package org.example.adapters;

import org.example.contract.repository.BuyerRepo;
import org.example.mappers.BuyerMapper;
import org.example.model.Buyer;
import org.example.repositories.BuyerRepository;

import java.util.Optional;

public class BuyerAdapter implements BuyerRepo {

    private final BuyerRepository repository;
    private final BuyerMapper mapper;

    public BuyerAdapter(BuyerRepository repository, BuyerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Buyer save(Buyer buyer) {
        final org.example.entities.Buyer entity = mapper.domainToEntity(buyer);
        final org.example.entities.Buyer save = repository.save(entity);
        return mapper.entityToDomain(save);
    }

    @Override
    public Optional<Buyer> findById(Long id) {
        final Optional<org.example.entities.Buyer> byId = repository.findById(id);
        return Optional.ofNullable(mapper.entityToDomain(byId.orElse(null)));
    }

    @Override
    public void delete(Buyer buyer) {
        repository.deleteById(buyer.getId());
    }
}
