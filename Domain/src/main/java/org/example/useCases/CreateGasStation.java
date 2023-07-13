package org.example.useCases;

import org.example.contract.repository.*;
import org.example.contract.request.CreateGasStationRequest;
import org.example.contract.response.GasStationResponse;
import org.example.mappers.GasStationDomainMapper;
import org.example.model.GasStation;
import org.example.validators.CreateGasStationValidator;

import java.time.LocalDateTime;

public class CreateGasStation {
    private final CreateGasStationValidator validator;
    private final GasStationDomainMapper mapper;
    private final GasStationRepo gasStationRepo;
    private final PriceCategoryRepo priceCategoryRepo;
    private final RegionRepo regionRepo;
    private final PersonRepo personRepo;
    private final GroupRepo groupRepo;

    public CreateGasStation(CreateGasStationValidator validator,
                            GasStationDomainMapper mapper,
                            GasStationRepo gasStationRepo, PriceCategoryRepo priceCategoryRepo,
                            RegionRepo regionRepo,
                            PersonRepo personRepo,
                            GroupRepo groupRepo) {
        this.validator = validator;
        this.mapper = mapper;
        this.gasStationRepo = gasStationRepo;
        this.priceCategoryRepo = priceCategoryRepo;
        this.regionRepo = regionRepo;
        this.personRepo = personRepo;
        this.groupRepo = groupRepo;
    }

    public GasStationResponse execute(CreateGasStationRequest request){
        validator.validate(request);
        final GasStation gasStation = mapper.requestToDomain(request);
        gasStation.setCreatedAt(LocalDateTime.now());
        final GasStation save = gasStationRepo.save(gasStation);
        personRepo.findById(save.getOwner().getId()).ifPresent(save::setOwner);
        groupRepo.findById(save.getGroup().getId()).ifPresent(save::setGroup);
        regionRepo.findById(save.getRegion().getId()).ifPresent(save::setRegion);
        priceCategoryRepo.findById(save.getPriceCategory().getId()).ifPresent(save::setPriceCategory);
        return mapper.domainToResponse(save);
    }
}
