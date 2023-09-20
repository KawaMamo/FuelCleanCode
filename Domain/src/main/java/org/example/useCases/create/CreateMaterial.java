package org.example.useCases.create;

import org.example.contract.repository.MaterialRepo;
import org.example.contract.request.create.CreateMaterialRequest;
import org.example.contract.response.MaterialResponse;
import org.example.mappers.MaterialDomainMapper;
import org.example.model.Material;
import org.example.validators.create.CreateMaterialValidator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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
        material.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        final Material save = materialRepo.save(material);
        return mapper.domainToResponse(save);
    }
}
