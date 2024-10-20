package org.example.adapters;

import org.example.contract.repository.ReturnedMaterialRepo;
import org.example.mappers.ReturnedMaterialMapper;
import org.example.model.ReturnedMaterial;
import org.example.repositories.ReturnedMaterialRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

public class ReturnedMaterialAdapter implements ReturnedMaterialRepo {

    private final ReturnedMaterialRepository repository;
    private final ReturnedMaterialMapper mapper;

    public ReturnedMaterialAdapter(ReturnedMaterialRepository returnedMaterialRepository, ReturnedMaterialMapper mapper) {
        this.repository = returnedMaterialRepository;
        this.mapper = mapper;
    }

    @Override
    public ReturnedMaterial save(ReturnedMaterial returnedMaterial) {
        final org.example.entities.ReturnedMaterial entity = mapper.domainToEntity(returnedMaterial);
        final org.example.entities.ReturnedMaterial save = repository.save(entity);
        return mapper.entityToDomain(save);
    }

    @Override
    public Optional<ReturnedMaterial> findById(Long id) {
        final org.example.entities.ReturnedMaterial returnedMaterial = repository.findById(id).orElseThrow(NoSuchElementException::new);
        return Optional.ofNullable(mapper.entityToDomain(returnedMaterial));
    }

    @Override
    public void delete(ReturnedMaterial returnedMaterial) {
        repository.deleteById(returnedMaterial.getId());
    }
}
