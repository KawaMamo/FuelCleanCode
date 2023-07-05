package org.example.useCases;

import org.example.contract.repository.MaterialRepo;
import org.example.contract.request.CreateMaterialRequest;
import org.example.contract.response.MaterialResponse;
import org.example.mappers.MaterialDomainMapper;
import org.example.model.Material;
import org.example.validators.CreateMaterialValidator;

import java.time.LocalDateTime;

public class CreateMaterial {
    private final CreateMaterialValidator validator;
    private final MaterialRepo materialRepo;
    private final MaterialDomainMapper mapper;

    public CreateMaterial(CreateMaterialValidator validator, MaterialRepo materialRepo, MaterialDomainMapper mapper) {
        this.validator = validator;
        this.materialRepo = materialRepo;
        this.mapper = mapper;
    }

    public MaterialResponse execute(CreateMaterialRequest request){
        validator.validate(request);
        final Material material = mapper.requestToDomain(request);
        final Material save = materialRepo.save(material);
        save.setCreatedAt(LocalDateTime.now());
        return mapper.domainToResponse(save);
    }
}
