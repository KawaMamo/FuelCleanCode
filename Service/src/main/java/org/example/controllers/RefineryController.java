package org.example.controllers;


import org.example.contract.request.create.CreateRefineryRequest;
import org.example.contract.request.update.UpdateRefineryRequest;
import org.example.contract.response.RefineryResponse;
import org.example.entities.PlaceEntity;
import org.example.entities.RefineryEntity;
import org.example.mappers.RefineryMapper;
import org.example.model.Refinery;
import org.example.repositories.RefineryRepository;
import org.example.specifications.CriteriaArrayToList;
import org.example.specifications.FilterSpecifications;
import org.example.specifications.SearchCriteria;
import org.example.specifications.SearchFilter;
import org.example.useCases.create.CreateRefinery;
import org.example.useCases.delete.DeleteRefinery;
import org.example.useCases.update.UpdateRefinery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/refinery")
public class RefineryController {

    private final CreateRefinery createRefinery;
    private final RefineryRepository refineryRepository;
    private final RefineryMapper placeMapper;
    private final PagedResourcesAssembler assembler;
    private final UpdateRefinery updateRefinery;
    private final DeleteRefinery deleteRefinery;

    public RefineryController(CreateRefinery createRefinery,
                              RefineryRepository refineryRepository,
                              RefineryMapper placeMapper,
                              PagedResourcesAssembler assembler, UpdateRefinery updateRefinery, DeleteRefinery deleteRefinery) {
        this.createRefinery = createRefinery;
        this.refineryRepository = refineryRepository;
        this.placeMapper = placeMapper;
        this.assembler = assembler;
        this.updateRefinery = updateRefinery;
        this.deleteRefinery = deleteRefinery;
    }

    @PostMapping
    public RefineryResponse createRefinery(@RequestBody CreateRefineryRequest request){
        return createRefinery.execute(request);
    }

    @GetMapping
    PagedModel<RefineryResponse> listRefineries(SearchFilter filter, Pageable pageable){
        final SearchFilter refineryFilter = new SearchFilter(new String[]{"placeType"},
                new String[]{"Refinery"},
                new String[]{":"},
                null);
        final List<SearchCriteria> criteriaList = CriteriaArrayToList.getCriteriaList(filter);
        final List<SearchCriteria> refineryCriteria = CriteriaArrayToList.getCriteriaList(refineryFilter);
        criteriaList.addAll(refineryCriteria);
        final FilterSpecifications<PlaceEntity> specifications = new FilterSpecifications<>(criteriaList);
        final Page<Refinery> page = refineryRepository.findAll(specifications, pageable)
                .map((PlaceEntity refineryEntity) -> placeMapper.toDomain((RefineryEntity) refineryEntity));
        return assembler.toModel(page);
    }

    @PatchMapping
    public ResponseEntity<RefineryResponse> updateRefinery(@RequestBody UpdateRefineryRequest request){
        return ResponseEntity.ok(updateRefinery.execute(request));
    }
    @DeleteMapping("/{id}")
    public RefineryResponse deleteRefinery(@PathVariable Long id){
        return deleteRefinery.execute(id);
    }
}
