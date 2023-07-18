package org.example.useCases;

import org.example.contract.repository.GasStationRepo;
import org.example.contract.repository.MaterialRepo;
import org.example.contract.repository.TransferMaterialRepo;
import org.example.contract.request.CreateTransferMaterialRequest;
import org.example.contract.response.TransferMaterialResponse;
import org.example.mappers.TransferMaterialDomainMapper;
import org.example.model.TransferMaterials;
import org.example.validators.CreateTransferMaterialValidator;

import java.time.LocalDateTime;

public class CreateTransferMaterial {
    private final CreateTransferMaterialValidator validator;
    private final TransferMaterialDomainMapper mapper;
    private final TransferMaterialRepo transferMaterialRepo;
    private final GasStationRepo gasStationRepo;
    private final MaterialRepo materialRepo;

    public CreateTransferMaterial(CreateTransferMaterialValidator validator,
                                  TransferMaterialDomainMapper mapper,
                                  TransferMaterialRepo transferMaterialRepo, GasStationRepo gasStationRepo, MaterialRepo materialRepo) {
        this.validator = validator;
        this.mapper = mapper;
        this.transferMaterialRepo = transferMaterialRepo;
        this.gasStationRepo = gasStationRepo;
        this.materialRepo = materialRepo;
    }

    public TransferMaterialResponse execute(CreateTransferMaterialRequest request){
        validator.validate(request);
        final TransferMaterials transferMaterials = mapper.requestToDomain(request);
        transferMaterials.setCreatedAt(LocalDateTime.now());
        final TransferMaterials save = transferMaterialRepo.save(transferMaterials);
        gasStationRepo.findById(save.getSource().getId()).ifPresent(save::setSource);
        gasStationRepo.findById(save.getDestination().getId()).ifPresent(save::setDestination);
        materialRepo.findById(save.getMaterial().getId()).ifPresent(save::setMaterial);
        return mapper.domainToResponse(save);
    }
}
