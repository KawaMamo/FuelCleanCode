package org.example.mappers;

import org.example.entities.*;
import org.example.model.*;
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

        if(source instanceof Region){
            return RegionToEntity((Region) source);
        }
        return null;
    }

    RegionEntity RegionToEntity(Region source);

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
        
        if(source instanceof RegionEntity){
            return RegionToDomain((RegionEntity) source);
        }
        return null;
    }

    Region RegionToDomain(RegionEntity source);

    GasStation stationToDomain(GasStationEntity gasStation);
    Refinery refineryToDomain(RefineryEntity refinery);
}
