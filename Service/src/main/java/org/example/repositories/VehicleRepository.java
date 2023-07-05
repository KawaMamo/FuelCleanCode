package org.example.repositories;

import org.example.repositories.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long>, PagingAndSortingRepository<Vehicle, Long>,
        JpaSpecificationExecutor<Vehicle> {

}
