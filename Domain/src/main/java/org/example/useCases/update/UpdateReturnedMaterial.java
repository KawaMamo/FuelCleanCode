package org.example.useCases.update;

import org.example.contract.repository.ReturnedMaterialRepo;
import org.example.contract.request.update.UpdateReturnedMaterialRequest;
import org.example.contract.response.ReturnedMaterialResponse;
import org.example.mappers.ReturnedMaterialDomainMapper;
import org.example.model.ReturnedMaterial;
import org.example.validators.update.UpdateReturnedMaterialValidator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;

public class UpdateReturnedMaterial {
    private final UpdateReturnedMaterialValidator updateReturnedMaterialValidator;
    private final ReturnedMaterialDomainMapper updateReturnedMaterialDomainMapper;
    private final ReturnedMaterialRepo returnReturnedMaterialRepo;

    public UpdateReturnedMaterial(UpdateReturnedMaterialValidator updateReturnedMaterialValidator,
                                  ReturnedMaterialDomainMapper updateReturnedMaterialDomainMapper,
                                  ReturnedMaterialRepo returnReturnedMaterialRepo) {
        this.updateReturnedMaterialValidator = updateReturnedMaterialValidator;
        this.updateReturnedMaterialDomainMapper = updateReturnedMaterialDomainMapper;
        this.returnReturnedMaterialRepo = returnReturnedMaterialRepo;
    }

    public ReturnedMaterialResponse execute(UpdateReturnedMaterialRequest request){
        final ReturnedMaterial original = returnReturnedMaterialRepo.findById(request.getId()).orElseThrow(NoSuchElementException::new);
        updateReturnedMaterialValidator.validate(request);
        final ReturnedMaterial domain = updateReturnedMaterialDomainMapper.toDomain(request);
        domain.setUpdatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        domain.setCreatedAt(original.getCreatedAt());
        final ReturnedMaterial save = returnReturnedMaterialRepo.save(domain);
        return updateReturnedMaterialDomainMapper.toResponse(save);
    }
}
