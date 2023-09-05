package org.example.adapters;

import org.example.contract.repository.GasStationRepo;
import org.example.entities.GasStationEntity;
import org.example.entities.PlaceEntity;
import org.example.exceptions.DomainValidationException;
import org.example.mappers.GasStationMapper;
import org.example.model.GasStation;
import org.example.repositories.GasStationRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;
import java.util.Optional;

public class GasStationAdapter implements GasStationRepo {
    private final GasStationRepository gasStationRepository;
    private final GasStationMapper mapper;


    public GasStationAdapter(GasStationRepository gasStationRepository, GasStationMapper mapper) {
        this.gasStationRepository = gasStationRepository;
        this.mapper = mapper;
    }

    @Override
    public GasStation save(GasStation gasStation) {
        final GasStationEntity gasStationEntity = mapper.domainToEntity(gasStation);
        gasStationEntity.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        gasStationEntity.setPlaceType("GasStation");
        final GasStationEntity save = gasStationRepository.save(gasStationEntity);
        return mapper.entityToDomain(save);
    }

    @Override
    public Optional<GasStation> findById(Long id) {
        final Optional<PlaceEntity> byId = gasStationRepository.findById(id);
        final PlaceEntity place = byId.orElseThrow(NoSuchElementException::new);
        GasStationEntity gasStationEntity;
        if(place instanceof GasStationEntity){
            gasStationEntity = (GasStationEntity) place;
        }else {
            //throw new NoSuchElementException("No such Gas Station");
            gasStationEntity = null;
        }

        final GasStation gasStation = mapper.entityToDomain(gasStationEntity);
        return Optional.ofNullable(gasStation);
    }

    @Override
    public void delete(GasStation gasStation) {
        gasStationRepository.delete(mapper.domainToEntity(gasStation));
    }
}
