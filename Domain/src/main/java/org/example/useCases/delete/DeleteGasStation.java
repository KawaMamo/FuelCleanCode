package org.example.useCases.delete;

import org.example.contract.repository.GasStationRepo;
import org.example.contract.response.GasStationResponse;
import org.example.mappers.GasStationDomainMapper;
import org.example.model.GasStation;

import java.util.NoSuchElementException;

public class DeleteGasStation {
    private final GasStationRepo gasStationRepo;
    private final GasStationDomainMapper gasStationDomainMapper;
    public DeleteGasStation(GasStationRepo gasStationRepo, GasStationDomainMapper gasStationDomainMapper) {
        this.gasStationRepo = gasStationRepo;
        this.gasStationDomainMapper = gasStationDomainMapper;
    }
    public GasStationResponse execute(Long id){
        final GasStation gasStation = gasStationRepo.findById(id).orElseThrow(NoSuchElementException::new);
        gasStationRepo.delete(gasStation);
        return gasStationDomainMapper.domainToResponse(gasStation);
    }
}
