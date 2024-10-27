package org.example.controllers;

import org.example.contract.request.create.CreateOfficePaymentRequest;
import org.example.contract.request.update.UpdateOfficePaymentRequest;
import org.example.contract.response.OfficePaymentResponse;
import org.example.entities.OfficePayment;
import org.example.mappers.OfficePaymentMapper;
import org.example.repositories.OfficePaymentRepository;
import org.example.specifications.CriteriaArrayToList;
import org.example.specifications.FilterSpecifications;
import org.example.specifications.SearchCriteria;
import org.example.specifications.SearchFilter;
import org.example.useCases.create.CreateOfficePayment;
import org.example.useCases.delete.DeleteOfficePayment;
import org.example.useCases.update.UpdateOfficePayment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/payment-controller")
public class OfficePaymentController {
    private final PagedResourcesAssembler pagedResourcesAssembler;
    private final CreateOfficePayment createOfficePayment;
    private final UpdateOfficePayment updateOfficePayment;
    private final DeleteOfficePayment deleteOfficePayment;
    private final OfficePaymentRepository repository;
    private final OfficePaymentMapper mapper;

    public OfficePaymentController(PagedResourcesAssembler pagedResourcesAssembler,
                                   CreateOfficePayment createOfficePayment,
                                   UpdateOfficePayment updateOfficePayment,
                                   DeleteOfficePayment deleteOfficePayment,
                                   OfficePaymentRepository repository,
                                   OfficePaymentMapper mapper) {
        this.pagedResourcesAssembler = pagedResourcesAssembler;
        this.createOfficePayment = createOfficePayment;
        this.updateOfficePayment = updateOfficePayment;
        this.deleteOfficePayment = deleteOfficePayment;
        this.repository = repository;
        this.mapper = mapper;
    }

    @PostMapping
    public OfficePaymentResponse create(@RequestBody CreateOfficePaymentRequest request){
        return createOfficePayment.create(request);
    }

    @PatchMapping
    public ResponseEntity<OfficePaymentResponse> update(@RequestBody UpdateOfficePaymentRequest request){
        return ResponseEntity.ok(updateOfficePayment.update(request));
    }

    @DeleteMapping("/{id}")
    public OfficePaymentResponse delete(@PathVariable Long id){
        return deleteOfficePayment.delete(id);
    }

    @GetMapping
    PagedModel<OfficePaymentResponse> get(SearchFilter filter, Pageable pageable) {
        final List<SearchCriteria> criteriaList = CriteriaArrayToList.getCriteriaList(filter);
        final FilterSpecifications<OfficePayment> specifications = new FilterSpecifications<>(criteriaList);
        final Page<org.example.model.OfficePayment> page = repository.findAll(specifications, pageable).map(mapper::entityToDomain);
        return pagedResourcesAssembler.toModel(page);
    }

    @GetMapping("/all")
    public List<org.example.model.OfficePayment> getAll(){
        return repository.findAll().stream().map(mapper::entityToDomain).toList();
    }
}
