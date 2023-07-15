package org.example.mappers;

import org.example.entities.GasStationEntity;
import org.example.entities.PlaceEntity;
import org.example.entities.RefineryEntity;
import org.example.entities.TransLineEntity;
import org.example.model.GasStation;
import org.example.model.Place;
import org.example.model.Refinery;
import org.example.model.TransLine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public interface TransLineMapper {
    @Mapping(source = "source", target = "source", qualifiedByName = "placeToEntity")
    @Mapping(source = "destination", target = "destination", qualifiedByName = "placeToEntity")
    @Mapping(source = "fee.currency", target = "feeCurrency")
    @Mapping(source = "fee.amount", target = "feeAmount")
    TransLineEntity domainToEntity(TransLine transLine);
    @Mapping(source = "source", target = "source", qualifiedByName = "placeToDomain")
    @Mapping(source = "destination", target = "destination", qualifiedByName = "placeToDomain")
    @Mapping(target = "fee.currency", source = "feeCurrency")
    @Mapping(target = "fee.amount", source = "feeAmount")
    TransLine entityToDomain(TransLineEntity transLine);

    @Named("placeToEntity")
    default PlaceEntity toEntity(Place source){
        if(source instanceof GasStation){
            return stationToEntity((GasStation) source);
        }

        if(source instanceof Refinery){
            return refineryToEntity((Refinery) source);
        }
        return null;
    }
    GasStationEntity stationToEntity(GasStation gasStation);
    RefineryEntity refineryToEntity(Refinery refinery);

    @Named("placeToDomain")
    default Place toDomain(PlaceEntity source){
        if(source instanceof GasStationEntity){
            return stationToDomain((GasStationEntity) source);
        }

        if(source instanceof RefineryEntity){
            return refineryToDomain((RefineryEntity) source);
        }
        return null;
    }

    GasStation stationToDomain(GasStationEntity gasStation);
    Refinery refineryToDomain(RefineryEntity refinery);
}
