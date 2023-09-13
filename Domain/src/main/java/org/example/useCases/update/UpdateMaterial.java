package org.example.useCases.update;

import org.example.contract.repository.MaterialRepo;
import org.example.contract.request.update.UpdateMaterialRequest;
import org.example.contract.response.MaterialResponse;
import org.example.mappers.MaterialDomainMapper;
import org.example.model.Material;
import org.example.validators.update.UpdateMaterialValidator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;

public class UpdateMaterial {

    private final UpdateMaterialValidator updateMaterialValidator;
    private final MaterialDomainMapper mapper;
    private final MaterialRepo materialRepo;

    public UpdateMaterial(UpdateMaterialValidator updateMaterialValidator,
                          MaterialDomainMapper mapper,
                          MaterialRepo materialRepo) {
        this.updateMaterialValidator = updateMaterialValidator;
        this.mapper = mapper;
        this.materialRepo = materialRepo;
    }

    public MaterialResponse execute(UpdateMaterialRequest request){
        final Material original = materialRepo.findById(request.getId()).orElseThrow(NoSuchElementException::new);
        updateMaterialValidator.validate(request);
        final Material material = mapper.requestToDomain(request);
        material.setCreatedAt(original.getCreatedAt());
        material.setUpdatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        final Material save = materialRepo.save(material);
        return mapper.domainToResponse(save);
    }
}
