package org.example.useCases.delete;

import org.example.contract.repository.ReturnedMaterialRepo;
import org.example.contract.response.ReturnedMaterialResponse;
import org.example.mappers.ReturnedMaterialDomainMapper;
import org.example.model.ReturnedMaterial;

import java.util.NoSuchElementException;

public class DeleteReturnedMaterial {
    public final ReturnedMaterialRepo repo;
    public final ReturnedMaterialDomainMapper mapper;

    public DeleteReturnedMaterial(ReturnedMaterialRepo repo, ReturnedMaterialDomainMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public ReturnedMaterialResponse execute(Long id){
        final ReturnedMaterial returnedMaterial = repo.findById(id).orElseThrow(NoSuchElementException::new);
        repo.delete(returnedMaterial);
        return mapper.toResponse(returnedMaterial);
    }
}
