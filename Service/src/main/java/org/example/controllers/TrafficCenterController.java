package org.example.controllers;

import org.example.contract.request.CreateTrafficCenterRequest;
import org.example.contract.response.TrafficCenterResponse;
import org.example.entities.TrafficCenterEntity;
import org.example.mappers.TrafficCenterMapper;
import org.example.model.TrafficCenter;
import org.example.repositories.TrafficCenterRepository;
import org.example.specifications.CriteriaArrayToList;
import org.example.specifications.FilterSpecifications;
import org.example.specifications.SearchCriteria;
import org.example.specifications.SearchFilter;
import org.example.useCases.CreateTrafficCenter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/traffic-center")
public class TrafficCenterController {

    private final CreateTrafficCenter createTrafficCenter;
    private final TrafficCenterRepository trafficCenterRepository;
    private final TrafficCenterMapper trafficCenterMapper;
    private final PagedResourcesAssembler centerPagedResourcesAssembler;

    public TrafficCenterController(CreateTrafficCenter createTrafficCenter,
                                   TrafficCenterRepository trafficCenterRepository,
                                   TrafficCenterMapper trafficCenterMapper,
                                   PagedResourcesAssembler<TrafficCenter> centerPagedResourcesAssembler) {
        this.createTrafficCenter = createTrafficCenter;
        this.trafficCenterRepository = trafficCenterRepository;
        this.trafficCenterMapper = trafficCenterMapper;
        this.centerPagedResourcesAssembler = centerPagedResourcesAssembler;
    }
    @PostMapping
    TrafficCenterResponse createTrafficCenter(@RequestBody CreateTrafficCenterRequest request){
        return createTrafficCenter.execute(request);
    }

    @GetMapping
    public PagedModel<TrafficCenterResponse> listTrafficCenters(SearchFilter filter, Pageable pageable){
        final List<SearchCriteria> criteriaList = CriteriaArrayToList.getCriteriaList(filter);
        final FilterSpecifications<TrafficCenterEntity> specifications = new FilterSpecifications<>(criteriaList);
        final Page<TrafficCenter> page = trafficCenterRepository.findAll(specifications, pageable)
                .map(trafficCenterMapper::entityToDomain);
        return centerPagedResourcesAssembler.toModel(page);
    }

}
