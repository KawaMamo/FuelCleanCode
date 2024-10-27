package org.example.controllers;

import org.example.contract.request.create.CreateSellerPaymentRequest;
import org.example.contract.request.update.UpdateSellerPaymentRequest;
import org.example.contract.response.SellerPaymentResponse;
import org.example.entities.SellerPayment;
import org.example.mappers.SellerPaymentMapper;
import org.example.repositories.SellerPaymentRepository;
import org.example.specifications.CriteriaArrayToList;
import org.example.specifications.FilterSpecifications;
import org.example.specifications.SearchCriteria;
import org.example.specifications.SearchFilter;
import org.example.useCases.create.CreateSellerPayment;
import org.example.useCases.delete.DeleteSellerPayment;
import org.example.useCases.update.UpdateSellerPayment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/seller-payment")
public class SellerPaymentController {
    private final PagedResourcesAssembler pagedResourcesAssembler;
    private final CreateSellerPayment createSellerPayment;
    private final UpdateSellerPayment updateSellerPayment;
    private final DeleteSellerPayment deleteSellerPayment;
    private final SellerPaymentRepository repository;
    private final SellerPaymentMapper mapper;

    public SellerPaymentController(PagedResourcesAssembler pagedResourcesAssembler,
                                   CreateSellerPayment createSellerPayment,
                                   UpdateSellerPayment updateSellerPayment,
                                   DeleteSellerPayment deleteSellerPayment,
                                   SellerPaymentRepository repository,
                                   SellerPaymentMapper mapper) {
        this.pagedResourcesAssembler = pagedResourcesAssembler;
        this.createSellerPayment = createSellerPayment;
        this.updateSellerPayment = updateSellerPayment;
        this.deleteSellerPayment = deleteSellerPayment;
        this.repository = repository;
        this.mapper = mapper;
    }

    @PostMapping
    public SellerPaymentResponse create(@RequestBody CreateSellerPaymentRequest request){
        return createSellerPayment.create(request);
    }

    @PatchMapping
    public ResponseEntity<SellerPaymentResponse> update(@RequestBody UpdateSellerPaymentRequest request){
        return ResponseEntity.ok(updateSellerPayment.update(request));
    }

    @DeleteMapping("/{id}")
    public SellerPaymentResponse delete(@PathVariable Long id){
        return deleteSellerPayment.delete(id);
    }

    @GetMapping
    public PagedModel<SellerPaymentResponse> get(SearchFilter filter, Pageable pageable) {
        final List<SearchCriteria> criteriaList = CriteriaArrayToList.getCriteriaList(filter);
        final FilterSpecifications<SellerPayment> specifications = new FilterSpecifications<>(criteriaList);
        final Page<org.example.model.SellerPayment> page = repository.findAll(specifications, pageable).map(mapper::entityToDomain);
        return pagedResourcesAssembler.toModel(page);
    }

    @GetMapping("/all")
    public List<org.example.model.SellerPayment> getAll() {
        return repository.findAll().stream().map(mapper::entityToDomain).toList();
    }
}
