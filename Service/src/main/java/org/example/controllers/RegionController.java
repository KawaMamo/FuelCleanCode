package org.example.controllers;

import org.example.contract.request.create.CreateRegionRequest;
import org.example.contract.request.update.UpdateRegionRequest;
import org.example.contract.response.RegionResponse;
import org.example.entities.RegionEntity;
import org.example.mappers.RegionMapper;
import org.example.model.Region;
import org.example.repositories.RegionRepository;
import org.example.specifications.CriteriaArrayToList;
import org.example.specifications.FilterSpecifications;
import org.example.specifications.SearchCriteria;
import org.example.specifications.SearchFilter;
import org.example.useCases.create.CreateRegion;
import org.example.useCases.delete.DeleteRegion;
import org.example.useCases.update.UpdateRegion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/region")
public class RegionController {
    private final CreateRegion createRegion;
    private final RegionRepository regionRepository;
    private final RegionMapper regionMapper;
    private final PagedResourcesAssembler assembler;
    private final UpdateRegion updateRegion;
    private final DeleteRegion deleteRegion;

    public RegionController(CreateRegion createRegion,
                            RegionRepository regionRepository,
                            RegionMapper regionMapper,
                            PagedResourcesAssembler assembler,
                            UpdateRegion updateRegion, DeleteRegion deleteRegion) {
        this.createRegion = createRegion;
        this.regionRepository = regionRepository;
        this.regionMapper = regionMapper;
        this.assembler = assembler;
        this.updateRegion = updateRegion;
        this.deleteRegion = deleteRegion;
    }

    @PostMapping
    public RegionResponse createRegion(@RequestBody CreateRegionRequest request){
        return createRegion.execute(request);
    }

    @GetMapping
    public PagedModel<RegionResponse> listRegion(SearchFilter searchFilter, Pageable pageable){
        final List<SearchCriteria> criteriaList = CriteriaArrayToList.getCriteriaList(searchFilter);
        final FilterSpecifications<RegionEntity> specifications = new FilterSpecifications<>(criteriaList);
        final Page<Region> page = regionRepository.findAll(specifications, pageable).map(regionMapper::entityToDomain);
        return assembler.toModel(page);
    }

    @PatchMapping
    public ResponseEntity<RegionResponse> updateRegion(@RequestBody UpdateRegionRequest request){
        return ResponseEntity.ok(updateRegion.execute(request));
    }
    @DeleteMapping("/{id}")
    public RegionResponse delete(@PathVariable Long id){
        return deleteRegion.execute(id);
    }
}
