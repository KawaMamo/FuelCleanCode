package org.example.contract.repository;

import org.example.model.Region;

import java.util.Optional;

public interface RegionRepo {
    Region save(Region region);
    Optional<Region> findById(Long id);
}
