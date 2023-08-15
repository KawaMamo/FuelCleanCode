package org.example.useCases.delete;

import org.example.contract.repository.MaterialRepo;
import org.example.contract.response.MaterialResponse;
import org.example.mappers.MaterialDomainMapper;
import org.example.model.Material;

import java.util.NoSuchElementException;

public class DeleteMaterial {
    private final MaterialRepo materialRepo;
    private final MaterialDomainMapper mapper;
    public DeleteMaterial(MaterialRepo materialRepo, MaterialDomainMapper mapper) {
        this.materialRepo = materialRepo;
        this.mapper = mapper;
    }
    public MaterialResponse execute(Long id){
        final Material material = materialRepo.findById(id).orElseThrow(NoSuchElementException::new);
        materialRepo.delete(material);
        return mapper.domainToResponse(material);
    }
}
