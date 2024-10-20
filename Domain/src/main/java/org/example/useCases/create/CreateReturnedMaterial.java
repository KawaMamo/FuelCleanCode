package org.example.useCases.create;

import org.example.contract.repository.BuyerRepo;
import org.example.contract.repository.GasStationRepo;
import org.example.contract.repository.MaterialRepo;
import org.example.contract.repository.ReturnedMaterialRepo;
import org.example.contract.request.create.CreateReturnedMaterialRequest;
import org.example.contract.response.ReturnedMaterialResponse;
import org.example.mappers.ReturnedMaterialDomainMapper;
import org.example.model.ReturnedMaterial;
import org.example.validators.create.CreateReturnedMaterialValidator;

import java.time.LocalDateTime;

public class CreateReturnedMaterial {
    private final CreateReturnedMaterialValidator validator;
    private final ReturnedMaterialDomainMapper mapper;
    private final ReturnedMaterialRepo returnedMaterialRepo;
    private final BuyerRepo buyerRepo;
    private final MaterialRepo materialRepo;
    private final GasStationRepo gasStationRepo;

    public CreateReturnedMaterial(CreateReturnedMaterialValidator validator,
                                  ReturnedMaterialDomainMapper mapper,
                                  ReturnedMaterialRepo returnedMaterialRepo,
                                  BuyerRepo buyerRepo,
                                  MaterialRepo materialRepo,
                                  GasStationRepo gasStationRepo) {
        this.validator = validator;
        this.mapper = mapper;
        this.returnedMaterialRepo = returnedMaterialRepo;
        this.buyerRepo = buyerRepo;
        this.materialRepo = materialRepo;
        this.gasStationRepo = gasStationRepo;
    }

    public ReturnedMaterialResponse execute(CreateReturnedMaterialRequest request){
        validator.validate(request);
        final ReturnedMaterial returnedMaterial = mapper.toDomain(request);
        returnedMaterial.setCreatedAt(LocalDateTime.now());
        final ReturnedMaterial save = returnedMaterialRepo.save(returnedMaterial);
        buyerRepo.findById(request.getBuyerId()).ifPresent(save::setBuyer);
        materialRepo.findById(request.getMaterialId()).ifPresent(save::setMaterial);
        gasStationRepo.findById(request.getGasStationId()).ifPresent(save::setGasStation);
        return mapper.toResponse(save);
    }
}
