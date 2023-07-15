package org.example.mappers;

import org.example.entities.GasStationEntity;
import org.example.entities.PlaceEntity;
import org.example.entities.RefineryEntity;
import org.example.model.GasStation;
import org.example.model.Place;
import org.example.model.Refinery;
import org.mapstruct.Mapper;

@Mapper
public interface PlaceMapper {

    default Place toDomain(PlaceEntity source){
        if(source instanceof GasStationEntity){
            stationToDomain((GasStationEntity) source);
        }

        if(source instanceof RefineryEntity){
            refineryToDomain((RefineryEntity) source);
        }
        return null;
    }

    default PlaceEntity toEntity(Place source){
        if(source instanceof GasStation){
            stationToEntity((GasStation) source);
        }

        if(source instanceof Refinery){
            refineryToEntity((Refinery) source);
        }
        return null;
    }

    GasStationEntity stationToEntity(GasStation gasStation);
    RefineryEntity refineryToEntity(Refinery refinery);

    GasStation stationToDomain(GasStationEntity gasStation);
    Refinery refineryToDomain(RefineryEntity refinery);

}
