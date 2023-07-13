package org.example.contract.repository;

import org.example.model.Place;

import java.util.Optional;

public interface PlaceRepo {
    Place save(Place place);
    Optional<Place> findById(Long id);
}
