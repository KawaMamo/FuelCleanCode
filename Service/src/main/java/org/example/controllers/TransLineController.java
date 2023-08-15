package org.example.controllers;

import org.example.contract.request.create.CreateTransLineRequest;
import org.example.contract.request.update.UpdateTransLineRequest;
import org.example.contract.response.TransLineResponse;
import org.example.entities.TransLineEntity;
import org.example.mappers.TransLineMapper;
import org.example.model.TransLine;
import org.example.repositories.TransLineRepository;
import org.example.specifications.CriteriaArrayToList;
import org.example.specifications.FilterSpecifications;
import org.example.specifications.SearchCriteria;
import org.example.specifications.SearchFilter;
import org.example.useCases.create.CreateTransLine;
import org.example.useCases.delete.DeleteTransLine;
import org.example.useCases.update.UpdateTransLine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("app/v1/trans-line")
public class TransLineController {

    private final CreateTransLine createTransLine;
    private final TransLineRepository transLineRepository;
    private final TransLineMapper transLineMapper;
    private final PagedResourcesAssembler assembler;
    private final UpdateTransLine updateTransLine;
    private final DeleteTransLine deleteTransLine;

    public TransLineController(CreateTransLine createTransLine,
                               TransLineRepository transLineRepository,
                               TransLineMapper transLineMapper,
                               PagedResourcesAssembler assembler, UpdateTransLine updateTransLine, DeleteTransLine deleteTransLine) {
        this.createTransLine = createTransLine;
        this.transLineRepository = transLineRepository;
        this.transLineMapper = transLineMapper;
        this.assembler = assembler;
        this.updateTransLine = updateTransLine;
        this.deleteTransLine = deleteTransLine;
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
    @PatchMapping
    public ResponseEntity<TransLineResponse> update(@RequestBody UpdateTransLineRequest request){
        return ResponseEntity.ok(updateTransLine.execute(request));
    }

    @DeleteMapping("/{id}")
    public TransLineResponse delete(@PathVariable Long id){
        return deleteTransLine.execute(id);
    }
}
