package org.example.adapters;

import org.example.contract.repository.PlaceRepo;
import org.example.entities.PlaceEntity;
import org.example.mappers.PlaceMapper;
import org.example.model.Place;
import org.example.repositories.PlaceRepository;

import java.util.Optional;

public class PlaceAdapter implements PlaceRepo {

    private final PlaceRepository placeRepository;
    private final PlaceMapper placeMapper;

    public PlaceAdapter(PlaceRepository placeRepository, PlaceMapper placeMapper) {
        this.placeRepository = placeRepository;
        this.placeMapper = placeMapper;
    }

    @Override
    public Place save(Place place) {
        return null;
    }

    @Override
    public Optional<Place> findById(Long id) {
        final Optional<PlaceEntity> byId = placeRepository.findById(id);
        final PlaceEntity place = byId.orElse(null);
        return Optional.ofNullable(placeMapper.toDomain(place));
    }

    @Override
    public void delete(Place place) {
        placeRepository.delete(placeMapper.toEntity(place));
    }
}
