package org.example.mappers;

import org.example.contract.repository.GasStationRepo;
import org.example.contract.repository.RefineryRepo;
import org.example.contract.repository.RegionRepo;
import org.example.model.GasStation;
import org.example.model.Place;
import org.example.model.Refinery;
import org.example.model.Region;

import java.util.Optional;

public class PlaceTypeDetector {
    private final GasStationRepo gasStationRepo;
    private final RefineryRepo refineryRepo;
    private final RegionRepo regionRepo;
    public PlaceTypeDetector(GasStationRepo gasStationRepo, RefineryRepo refineryRepo, RegionRepo regionRepo) {
        this.gasStationRepo = gasStationRepo;
        this.refineryRepo = refineryRepo;
        this.regionRepo = regionRepo;
    }

    public Place detect(Long requestId){
        try {
            final Optional<GasStation> byId = gasStationRepo.findById(requestId);
            if(byId.isPresent()){
                return byId.get();
            }
        }catch (Exception ignored){}

        try {
            final Optional<Refinery> refinery = refineryRepo.findById(requestId);
            if(refinery.isPresent()){
                return refinery.orElse(null);
            }
        }catch (Exception ignored){}

        final Optional<Region> region = regionRepo.findById(requestId);
        return region.orElse(null);

    }
}
