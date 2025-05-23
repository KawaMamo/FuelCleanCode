package org.example.useCases.create;

import org.example.contract.repository.*;
import org.example.contract.request.create.CreateGasStationRequest;
import org.example.contract.response.GasStationResponse;
import org.example.mappers.GasStationDomainMapper;
import org.example.model.GasStation;
import org.example.validators.create.CreateGasStationValidator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CreateGasStation {
    private final CreateGasStationValidator validator;
    private final GasStationDomainMapper mapper;
    private final GasStationRepo gasStationRepo;
    private final PriceCategoryRepo priceCategoryRepo;
    private final RegionRepo regionRepo;
    private final PersonRepo personRepo;
    private final GroupRepo groupRepo;
    private final MaterialRepo materialRepo;

    public CreateGasStation(CreateGasStationValidator validator,
                            GasStationDomainMapper mapper,
                            GasStationRepo gasStationRepo, PriceCategoryRepo priceCategoryRepo,
                            RegionRepo regionRepo,
                            PersonRepo personRepo,
                            GroupRepo groupRepo, MaterialRepo materialRepo) {
        this.validator = validator;
        this.mapper = mapper;
        this.gasStationRepo = gasStationRepo;
        this.priceCategoryRepo = priceCategoryRepo;
        this.regionRepo = regionRepo;
        this.personRepo = personRepo;
        this.groupRepo = groupRepo;
        this.materialRepo = materialRepo;
    }

    public GasStationResponse execute(CreateGasStationRequest request){
        validator.validate(request);
        final GasStation gasStation = mapper.requestToDomain(request);
        gasStation.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        final GasStation save = gasStationRepo.save(gasStation);
        personRepo.findById(save.getOwner().getId()).ifPresent(save::setOwner);
        groupRepo.findById(save.getGroup().getId()).ifPresent(save::setGroup);
        regionRepo.findById(save.getRegion().getId()).ifPresent(save::setRegion);
        priceCategoryRepo.findById(save.getPriceCategory().getId()).ifPresent(save::setPriceCategory);
        materialRepo.findById(save.getMaterial().getId()).ifPresent(save::setMaterial);
        return mapper.domainToResponse(save);
    }
}
