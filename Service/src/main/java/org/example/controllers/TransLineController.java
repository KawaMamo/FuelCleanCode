package org.example.controllers;

import org.example.contract.request.CreateTransLineRequest;
import org.example.contract.response.TransLineResponse;
import org.example.entities.TransLineEntity;
import org.example.mappers.TransLineMapper;
import org.example.model.TransLine;
import org.example.repositories.TransLineRepository;
import org.example.specifications.CriteriaArrayToList;
import org.example.specifications.FilterSpecifications;
import org.example.specifications.SearchCriteria;
import org.example.specifications.SearchFilter;
import org.example.useCases.CreateTransLine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("app/v1/trans-line")
public class TransLineController {

    private final CreateTransLine createTransLine;
    private final TransLineRepository transLineRepository;
    private final TransLineMapper transLineMapper;
    private final PagedResourcesAssembler assembler;

    public TransLineController(CreateTransLine createTransLine,
                               TransLineRepository transLineRepository,
                               TransLineMapper transLineMapper,
                               PagedResourcesAssembler assembler) {
        this.createTransLine = createTransLine;
        this.transLineRepository = transLineRepository;
        this.transLineMapper = transLineMapper;
        this.assembler = assembler;
    }
    @PostMapping
    public TransLineResponse execute(@RequestBody CreateTransLineRequest request){
        return createTransLine.execute(request);
    }

    @GetMapping
    public PagedModel<TransLineResponse> transLines(SearchFilter filter, Pageable pageable){
        final List<SearchCriteria> criteriaList = CriteriaArrayToList.getCriteriaList(filter);
        final FilterSpecifications<TransLineEntity> specifications = new FilterSpecifications<>(criteriaList);
        final Page<TransLine> page = transLineRepository.findAll(specifications, pageable).map(transLineMapper::entityToDomain);
        return assembler.toModel(page);
    }
}
