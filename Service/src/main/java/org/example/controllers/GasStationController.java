package org.example.controllers;

import org.example.contract.request.create.CreateGasStationRequest;
import org.example.contract.request.update.UpdateGasStationRequest;
import org.example.contract.response.GasStationResponse;
import org.example.entities.GasStationEntity;
import org.example.entities.PlaceEntity;
import org.example.mappers.GasStationMapper;
import org.example.model.GasStation;
import org.example.repositories.GasStationRepository;
import org.example.specifications.CriteriaArrayToList;
import org.example.specifications.FilterSpecifications;
import org.example.specifications.SearchCriteria;
import org.example.specifications.SearchFilter;
import org.example.useCases.create.CreateGasStation;
import org.example.useCases.delete.DeleteGasStation;
import org.example.useCases.update.UpdateGasStation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1/gas-station")
public class GasStationController {
    private final CreateGasStation createGasStation;
    private final GasStationRepository gasStationRepository;
    private final GasStationMapper gasStationMapper;
    private final PagedResourcesAssembler pagedResourcesAssembler;
    private final UpdateGasStation updateGasStation;
    private final DeleteGasStation deleteGasStation;

    public GasStationController(CreateGasStation createGasStation,
                                GasStationRepository gasStationRepository,
                                GasStationMapper gasStationMapper,
                                PagedResourcesAssembler pagedResourcesAssembler,
                                UpdateGasStation updateGasStation, DeleteGasStation deleteGasStation) {
        this.createGasStation = createGasStation;
        this.gasStationRepository = gasStationRepository;
        this.gasStationMapper = gasStationMapper;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
        this.updateGasStation = updateGasStation;
        this.deleteGasStation = deleteGasStation;
    }

    @PostMapping
    public GasStationResponse createGasStation(@RequestBody CreateGasStationRequest request){
        return createGasStation.execute(request);
    }

    @GetMapping
    public PagedModel<GasStationResponse> listGasStation(SearchFilter searchFilter, Pageable pageable){
        final SearchFilter gasStationFilter = new SearchFilter(new String[]{"placeType"},
                new String[]{"GasStation"},
                new String[]{":"},
                null);
        final List<SearchCriteria> criteriaList = CriteriaArrayToList.getCriteriaList(searchFilter);
        final List<SearchCriteria> criteria = CriteriaArrayToList.getCriteriaList(gasStationFilter);
        criteriaList.addAll(criteria);
        final FilterSpecifications<PlaceEntity> specifications = new FilterSpecifications<>(criteriaList);
        final Page<GasStation> page = gasStationRepository.findAll(specifications, pageable)
                .map(place -> gasStationMapper.entityToDomain((GasStationEntity) place));
        return pagedResourcesAssembler.toModel(page);
    }

    @GetMapping("/all")
    public List<GasStation> getAll(){
        return gasStationRepository.findAll().stream()
                .filter(place -> place instanceof GasStationEntity)
                .map(gasStation -> gasStationMapper.entityToDomain((GasStationEntity) gasStation))
                .toList();
    }

    @PatchMapping
    public ResponseEntity<GasStationResponse> updateGasStation(@RequestBody UpdateGasStationRequest request){
        return ResponseEntity.ok(updateGasStation.execute(request));
    }

    @DeleteMapping("/{id}")
    public GasStationResponse delete(@PathVariable Long id){
        return deleteGasStation.execute(id);
    }

    @GetMapping("/{id}")
    public GasStation getByID(@PathVariable Long id){
        final PlaceEntity place = gasStationRepository.findById(id).orElse(null);
        if(place instanceof GasStationEntity)
            return gasStationMapper.entityToDomain((GasStationEntity) place);
        else throw new NoSuchElementException("No such Gas Station");
    }
}
