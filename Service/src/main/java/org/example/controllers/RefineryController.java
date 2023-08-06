package org.example.controllers;


import org.example.contract.request.CreateRefineryRequest;
import org.example.contract.response.CreateRefineryResponse;
import org.example.entities.PlaceEntity;
import org.example.entities.RefineryEntity;
import org.example.mappers.PlaceMapper;
import org.example.mappers.RefineryMapper;
import org.example.model.Place;
import org.example.model.Refinery;
import org.example.repositories.RefineryRepository;
import org.example.specifications.CriteriaArrayToList;
import org.example.specifications.FilterSpecifications;
import org.example.specifications.SearchCriteria;
import org.example.specifications.SearchFilter;
import org.example.useCases.CreateRefinery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/refinery")
public class RefineryController {

    private final CreateRefinery createRefinery;
    private final RefineryRepository refineryRepository;
    private final RefineryMapper placeMapper;
    private final PagedResourcesAssembler assembler;

    public RefineryController(CreateRefinery createRefinery,
                              RefineryRepository refineryRepository,
                              RefineryMapper placeMapper,
                              PagedResourcesAssembler assembler) {
        this.createRefinery = createRefinery;
        this.refineryRepository = refineryRepository;
        this.placeMapper = placeMapper;
        this.assembler = assembler;
    }

    @PostMapping
    public CreateRefineryResponse createRefinery(@RequestBody CreateRefineryRequest request){
        return createRefinery.execute(request);
    }

    @GetMapping
    PagedModel<CreateRefineryResponse> listRefineries(SearchFilter filter, Pageable pageable){
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
}
