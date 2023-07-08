package org.example.adapters;

import org.example.contract.repository.TrafficCenterRepo;
import org.example.mappers.TrafficCenterMapper;
import org.example.model.TrafficCenter;
import org.example.repositories.TrafficCenterRepository;
import org.example.entities.TrafficCenterEntity;

import java.time.LocalDateTime;
import java.util.Optional;

public class TrafficCenterAdapter implements TrafficCenterRepo {
    private final TrafficCenterRepository repository;
    private final TrafficCenterMapper mapper;

    public TrafficCenterAdapter(TrafficCenterRepository repository, TrafficCenterMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public TrafficCenter save(TrafficCenter trafficCenter) {
        final TrafficCenterEntity trafficCenterEntity = mapper.domainToEntity(trafficCenter);
        trafficCenterEntity.setCreatedAt(LocalDateTime.now());
        final TrafficCenterEntity save = repository.save(trafficCenterEntity);
        return mapper.entityToDomain(save);
    }

    @Override
    public Optional<TrafficCenter> findById(Long id) {
        final Optional<TrafficCenterEntity> byId = repository.findById(id);
        final TrafficCenterEntity trafficCenterEntity = byId.orElse(null);
        final TrafficCenter trafficCenter = mapper.entityToDomain(trafficCenterEntity);
        return Optional.ofNullable(trafficCenter);
    }
}
