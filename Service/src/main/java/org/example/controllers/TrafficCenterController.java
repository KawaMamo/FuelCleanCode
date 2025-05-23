package org.example.controllers;

import org.example.contract.request.create.CreateTrafficCenterRequest;
import org.example.contract.request.update.UpdateTrafficCenterRequest;
import org.example.contract.response.TrafficCenterResponse;
import org.example.entities.TrafficCenterEntity;
import org.example.mappers.TrafficCenterMapper;
import org.example.model.TrafficCenter;
import org.example.repositories.TrafficCenterRepository;
import org.example.specifications.CriteriaArrayToList;
import org.example.specifications.FilterSpecifications;
import org.example.specifications.SearchCriteria;
import org.example.specifications.SearchFilter;
import org.example.useCases.create.CreateTrafficCenter;
import org.example.useCases.delete.DeleteTrafficCenter;
import org.example.useCases.update.UpdateTrafficCenter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/traffic-center")
public class TrafficCenterController {

    private final CreateTrafficCenter createTrafficCenter;
    private final TrafficCenterRepository trafficCenterRepository;
    private final TrafficCenterMapper trafficCenterMapper;
    private final PagedResourcesAssembler centerPagedResourcesAssembler;
    private final UpdateTrafficCenter updateTrafficCenter;
    private final DeleteTrafficCenter deleteTrafficCenter;

    public TrafficCenterController(CreateTrafficCenter createTrafficCenter,
                                   TrafficCenterRepository trafficCenterRepository,
                                   TrafficCenterMapper trafficCenterMapper,
                                   PagedResourcesAssembler centerPagedResourcesAssembler,
                                   UpdateTrafficCenter updateTrafficCenter, DeleteTrafficCenter deleteTrafficCenter) {
        this.createTrafficCenter = createTrafficCenter;
        this.trafficCenterRepository = trafficCenterRepository;
        this.trafficCenterMapper = trafficCenterMapper;
        this.centerPagedResourcesAssembler = centerPagedResourcesAssembler;
        this.updateTrafficCenter = updateTrafficCenter;
        this.deleteTrafficCenter = deleteTrafficCenter;
    }
    @PostMapping
    TrafficCenterResponse createTrafficCenter(@RequestBody CreateTrafficCenterRequest request){
        return createTrafficCenter.execute(request);
    }

    @GetMapping
    public PagedModel<TrafficCenterResponse> listTrafficCenters(SearchFilter filter, @PageableDefault(3000)Pageable pageable){
        final List<SearchCriteria> criteriaList = CriteriaArrayToList.getCriteriaList(filter);
        final FilterSpecifications<TrafficCenterEntity> specifications = new FilterSpecifications<>(criteriaList);
        final Page<TrafficCenter> page = trafficCenterRepository.findAll(specifications, pageable)
                .map(trafficCenterMapper::entityToDomain);
        return centerPagedResourcesAssembler.toModel(page);
    }

    @GetMapping("/all")
    public List<TrafficCenter> listAllTrafficCenters(){
        return trafficCenterRepository.findAll().stream().map(trafficCenterMapper::entityToDomain).toList();
    }

    @PatchMapping
    public ResponseEntity<TrafficCenterResponse> updateTrafficCenter(@RequestBody UpdateTrafficCenterRequest request){
        return ResponseEntity.ok(updateTrafficCenter.execute(request));
    }
    @DeleteMapping("/{id}")
    public TrafficCenterResponse delete(@PathVariable Long id){
        return deleteTrafficCenter.execute(id);
    }

}
