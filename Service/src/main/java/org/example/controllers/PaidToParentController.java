package org.example.controllers;

import org.example.contract.request.create.CreatePaidToParentRequest;
import org.example.contract.request.update.UpdatePaidToParentRequest;
import org.example.contract.response.PaidToParentResponse;
import org.example.entities.PaidToParent;
import org.example.mappers.PaidToParentMapper;
import org.example.repositories.PaidToParentRepository;
import org.example.specifications.CriteriaArrayToList;
import org.example.specifications.FilterSpecifications;
import org.example.specifications.SearchCriteria;
import org.example.specifications.SearchFilter;
import org.example.useCases.create.CreatePaidToParent;
import org.example.useCases.delete.DeletePaidToParent;
import org.example.useCases.update.UpdatePaidToParent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/paid-to-parent")
public class PaidToParentController {
    private final PagedResourcesAssembler pagedResourcesAssembler;
    private final CreatePaidToParent createPaidToParent;
    private final UpdatePaidToParent updatePaidToParent;
    private final DeletePaidToParent deletePaidToParent;
    private final PaidToParentMapper mapper;
    private final PaidToParentRepository repository;

    public PaidToParentController(PagedResourcesAssembler pagedResourcesAssembler,
                                  CreatePaidToParent createPaidToParent,
                                  UpdatePaidToParent updatePaidToParent,
                                  DeletePaidToParent deletePaidToParent,
                                  PaidToParentMapper mapper,
                                  PaidToParentRepository repository) {
        this.pagedResourcesAssembler = pagedResourcesAssembler;
        this.createPaidToParent = createPaidToParent;
        this.updatePaidToParent = updatePaidToParent;
        this.deletePaidToParent = deletePaidToParent;
        this.mapper = mapper;
        this.repository = repository;
    }

    @PostMapping
    public PaidToParentResponse create(@RequestBody CreatePaidToParentRequest request){
        return createPaidToParent.create(request);
    }

    @PatchMapping
    public ResponseEntity<PaidToParentResponse> update(@RequestBody UpdatePaidToParentRequest request){
        return ResponseEntity.ok(updatePaidToParent.update(request));
    }

    @DeleteMapping("/{id}")
    public PaidToParentResponse delete(@PathVariable Long id){
        return deletePaidToParent.delete(id);
    }

    @GetMapping
    PagedModel<PaidToParentResponse> get(SearchFilter filter, Pageable pageable){
        final List<SearchCriteria> criteriaList = CriteriaArrayToList.getCriteriaList(filter);
        final FilterSpecifications<PaidToParent> specifications = new FilterSpecifications<>(criteriaList);
        final Page<org.example.model.PaidToParent> page = repository.findAll(specifications, pageable).map(mapper::entityToDomain);
        return pagedResourcesAssembler.toModel(page);
    }

    @GetMapping("/all")
    public List<org.example.model.PaidToParent> getAll(){
        return repository.findAll().stream().map(mapper::entityToDomain).toList();
    }
}
