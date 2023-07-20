package org.example.controllers;

import org.example.contract.request.CreateGasStationRequest;
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
import org.example.useCases.CreateGasStation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/gas-station")
public class GasStationController {
    private final CreateGasStation createGasStation;
    private final GasStationRepository gasStationRepository;
    private final GasStationMapper gasStationMapper;
    private final PagedResourcesAssembler pagedResourcesAssembler;

    public GasStationController(CreateGasStation createGasStation,
                                GasStationRepository gasStationRepository,
                                GasStationMapper gasStationMapper,
                                PagedResourcesAssembler pagedResourcesAssembler) {
        this.createGasStation = createGasStation;
        this.gasStationRepository = gasStationRepository;
        this.gasStationMapper = gasStationMapper;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @PostMapping
    public GasStationResponse createGasStation(@RequestBody CreateGasStationRequest request){
        return createGasStation.execute(request);
    }

    @GetMapping
    public PagedModel<GasStationResponse> listGasStation(SearchFilter searchFilter, Pageable pageable){
        final List<SearchCriteria> criteriaList = CriteriaArrayToList.getCriteriaList(searchFilter);
        final FilterSpecifications<PlaceEntity> specifications = new FilterSpecifications<>(criteriaList);
        final Page<GasStation> page = gasStationRepository.findAll(specifications, pageable).map(place -> gasStationMapper.entityToDomain((GasStationEntity) place));
        return pagedResourcesAssembler.toModel(page);
    }
}
