package org.example.useCases.update;

import org.example.contract.repository.TransferMaterialRepo;
import org.example.contract.request.update.UpdateTransferMaterialRequest;
import org.example.contract.response.TransferMaterialResponse;
import org.example.mappers.TransferMaterialDomainMapper;
import org.example.model.TransferMaterials;
import org.example.validators.update.UpdateTransferMaterialValidator;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

public class UpdateTransferMaterial {
    private final UpdateTransferMaterialValidator validator;
    private final TransferMaterialRepo transferMaterialRepo;
    private final TransferMaterialDomainMapper mapper;

    public UpdateTransferMaterial(UpdateTransferMaterialValidator validator, TransferMaterialRepo transferMaterialRepo, TransferMaterialDomainMapper mapper) {
        this.validator = validator;
        this.transferMaterialRepo = transferMaterialRepo;
        this.mapper = mapper;
    }

    public TransferMaterialResponse execute(UpdateTransferMaterialRequest request){
        final TransferMaterials original = transferMaterialRepo.findById(request.getId()).orElseThrow(NoSuchElementException::new);
        validator.validate(request);
        final TransferMaterials transferMaterials = mapper.requestToDomain(request);
        transferMaterials.setCreatedAt(original.getCreatedAt());
        transferMaterials.setUpdatedAt(LocalDateTime.now());
        final TransferMaterials save = transferMaterialRepo.save(transferMaterials);
        return mapper.domainToResponse(save);
    }
}
