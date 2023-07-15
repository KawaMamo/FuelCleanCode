package org.example.mappers;

import org.example.contract.repository.GasStationRepo;
import org.example.contract.repository.PlaceRepo;
import org.example.contract.repository.RefineryRepo;
import org.example.model.GasStation;
import org.example.model.Place;
import org.example.model.Refinery;

import java.util.Optional;

public class PlaceTypeDetector {
    private final GasStationRepo gasStationRepo;
    private final RefineryRepo refineryRepo;
    public PlaceTypeDetector(GasStationRepo gasStationRepo, RefineryRepo refineryRepo) {
        this.gasStationRepo = gasStationRepo;
        this.refineryRepo = refineryRepo;
    }

    public Place detect(Long requestId){
        try {
            final Optional<GasStation> byId = gasStationRepo.findById(requestId);
            if(byId.isPresent()){
                return byId.get();
            }
        }catch (ClassCastException ignored){}

        final Optional<Refinery> refinery = refineryRepo.findById(requestId);
        return refinery.orElse(null);
    }
}
