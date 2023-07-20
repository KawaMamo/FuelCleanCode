package org.example.controllers;

import org.example.contract.request.CreateVehicleRequest;
import org.example.contract.response.VehicleResponse;
import org.example.entities.VehicleEntity;
import org.example.mappers.VehicleMapper;
import org.example.model.Vehicle;
import org.example.repositories.VehicleRepository;
import org.example.specifications.CriteriaArrayToList;
import org.example.specifications.FilterSpecifications;
import org.example.specifications.SearchCriteria;
import org.example.specifications.SearchFilter;
import org.example.useCases.CreateVehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/vehicle")
public class VehicleController {

    private final CreateVehicle createVehicle;
    private final VehicleRepository vehicleRepository;
    private final PagedResourcesAssembler pagedResourcesAssembler;
    private final VehicleMapper vehicleMapper;

    public VehicleController(CreateVehicle createVehicle,
                             VehicleRepository vehicleRepository,
                             PagedResourcesAssembler pagedResourcesAssembler, VehicleMapper vehicleMapper) {
        this.createVehicle = createVehicle;
        this.vehicleRepository = vehicleRepository;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
        this.vehicleMapper = vehicleMapper;
    }

    @PostMapping
    public VehicleResponse createVehicle(@RequestBody CreateVehicleRequest request){
        return createVehicle.execute(request);
    }

    @GetMapping
    public PagedModel<VehicleResponse> listVehicles(SearchFilter searchFilters, Pageable pageable){
        final List<SearchCriteria> criteriaList = CriteriaArrayToList.getCriteriaList(searchFilters);
        final FilterSpecifications<VehicleEntity> specifications = new FilterSpecifications<>(criteriaList);
        final Page<Vehicle> page = vehicleRepository.findAll(specifications, pageable).map(vehicleMapper::EntityToDomain);
        return pagedResourcesAssembler.toModel(page);
    }
}
