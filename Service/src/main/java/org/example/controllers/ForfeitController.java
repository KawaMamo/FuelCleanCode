package org.example.controllers;

import org.example.contract.request.CreateForfeitRequest;
import org.example.contract.response.ForfeitResponse;
import org.example.entities.ForfeitEntity;
import org.example.mappers.ForfeitMapper;
import org.example.model.Forfeit;
import org.example.repositories.ForfeitRepository;
import org.example.specifications.CriteriaArrayToList;
import org.example.specifications.FilterSpecifications;
import org.example.specifications.SearchCriteria;
import org.example.specifications.SearchFilter;
import org.example.useCases.CreateForfeit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/forfeit")
public class ForfeitController {

    private final CreateForfeit createForfeit;
    private final ForfeitRepository forfeitRepository;
    private final ForfeitMapper forfeitMapper;
    private final PagedResourcesAssembler pagedResourcesAssembler;

    public ForfeitController(CreateForfeit createForfeit,
                             ForfeitRepository forfeitRepository,
                             ForfeitMapper forfeitMapper,
                             PagedResourcesAssembler pagedResourcesAssembler) {
        this.createForfeit = createForfeit;
        this.forfeitRepository = forfeitRepository;
        this.forfeitMapper = forfeitMapper;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @PostMapping
    public ForfeitResponse createForfeit(@RequestBody CreateForfeitRequest request){
        return createForfeit.execute(request);
    }

    @GetMapping
    public PagedModel<ForfeitResponse> listForfeit(SearchFilter searchFilter, Pageable pageable){
        final List<SearchCriteria> criteriaList = CriteriaArrayToList.getCriteriaList(searchFilter);
        final FilterSpecifications<ForfeitEntity> specifications = new FilterSpecifications<>(criteriaList);
        final Page<Forfeit> page = forfeitRepository.findAll(specifications, pageable).map(forfeitMapper::entityToDomain);
        return pagedResourcesAssembler.toModel(page);
    }
}
