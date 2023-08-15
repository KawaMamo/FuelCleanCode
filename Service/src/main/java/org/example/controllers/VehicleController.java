package org.example.controllers;

import org.example.contract.request.create.CreateVehicleRequest;
import org.example.contract.request.update.UpdateVehicleRequest;
import org.example.contract.response.VehicleResponse;
import org.example.entities.VehicleEntity;
import org.example.mappers.VehicleMapper;
import org.example.model.Vehicle;
import org.example.repositories.VehicleRepository;
import org.example.specifications.CriteriaArrayToList;
import org.example.specifications.FilterSpecifications;
import org.example.specifications.SearchCriteria;
import org.example.specifications.SearchFilter;
import org.example.useCases.create.CreateVehicle;
import org.example.useCases.delete.DeleteVehicle;
import org.example.useCases.update.UpdateVehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/vehicle")
public class VehicleController {

    private final CreateVehicle createVehicle;
    private final VehicleRepository vehicleRepository;
    private final PagedResourcesAssembler pagedResourcesAssembler;
    private final VehicleMapper vehicleMapper;
    private final UpdateVehicle updateVehicle;
    private final DeleteVehicle deleteVehicle;

    public VehicleController(CreateVehicle createVehicle,
                             VehicleRepository vehicleRepository,
                             PagedResourcesAssembler pagedResourcesAssembler, VehicleMapper vehicleMapper, UpdateVehicle updateVehicle, DeleteVehicle deleteVehicle) {
        this.createVehicle = createVehicle;
        this.vehicleRepository = vehicleRepository;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
        this.vehicleMapper = vehicleMapper;
        this.updateVehicle = updateVehicle;
        this.deleteVehicle = deleteVehicle;
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

    @PatchMapping
    public ResponseEntity<VehicleResponse> updateVehicle(@RequestBody UpdateVehicleRequest request){
        return ResponseEntity.ok(updateVehicle.execute(request));
    }
    @DeleteMapping("/{id}")
    public VehicleResponse deleteVehicle(@PathVariable Long id){
        return deleteVehicle.execute(id);
    }
}
