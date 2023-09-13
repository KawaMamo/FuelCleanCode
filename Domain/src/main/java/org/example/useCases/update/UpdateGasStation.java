package org.example.useCases.update;

import org.example.contract.repository.GasStationRepo;
import org.example.contract.request.update.UpdateGasStationRequest;
import org.example.contract.response.GasStationResponse;
import org.example.mappers.GasStationDomainMapper;
import org.example.model.GasStation;
import org.example.validators.update.UpdateGasStationValidator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;
import java.util.Optional;

public class UpdateGasStation {
    private final UpdateGasStationValidator validator;
    private final GasStationDomainMapper mapper;
    private final GasStationRepo gasStationRepo;

    public UpdateGasStation(UpdateGasStationValidator validator,
                            GasStationDomainMapper mapper,
                            GasStationRepo gasStationRepo) {
        this.validator = validator;
        this.mapper = mapper;
        this.gasStationRepo = gasStationRepo;
    }

    public GasStationResponse execute(UpdateGasStationRequest request){
        final GasStation original = gasStationRepo.findById(request.getId()).orElseThrow(NoSuchElementException::new);
        validator.validate(request);
        final GasStation gasStation = mapper.requestToDomain(request);
        gasStation.setCreatedAt(original.getCreatedAt());
        gasStation.setUpdatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        final GasStation save = gasStationRepo.save(gasStation);
        return mapper.domainToResponse(save);
    }
}
