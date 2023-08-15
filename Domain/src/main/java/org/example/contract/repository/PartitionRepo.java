package org.example.contract.repository;

import org.example.model.Partition;

import java.util.Optional;

public interface PartitionRepo {
    Partition save(Partition partition);
    Optional<Partition> findById(Long id);
    void delete(Partition partition);
}
