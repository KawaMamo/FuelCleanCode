package org.example.controllers;

import org.example.contract.request.create.CreateClientPaymentRequest;
import org.example.contract.request.update.UpdateClientPaymentRequest;
import org.example.contract.response.ClientPaymentResponse;
import org.example.entities.ClientPayment;
import org.example.mappers.ClientPaymentMapper;
import org.example.repositories.ClientPaymentRepository;
import org.example.specifications.CriteriaArrayToList;
import org.example.specifications.FilterSpecifications;
import org.example.specifications.SearchCriteria;
import org.example.specifications.SearchFilter;
import org.example.useCases.create.CreateClientPayment;
import org.example.useCases.delete.DeleteClientPayment;
import org.example.useCases.update.UpdateClientPayment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/client-payment")
public class ClientPaymentController {

    private final PagedResourcesAssembler pagedResourcesAssembler;
    private final CreateClientPayment createClientPayment;
    private final UpdateClientPayment updateClientPayment;
    private final DeleteClientPayment deleteClientPayment;
    private final ClientPaymentMapper mapper;
    private final ClientPaymentRepository repository;

    public ClientPaymentController(PagedResourcesAssembler pagedResourcesAssembler,
                                   CreateClientPayment createClientPayment,
                                   UpdateClientPayment updateClientPayment,
                                   DeleteClientPayment deleteClientPayment,
                                   ClientPaymentMapper mapper,
                                   ClientPaymentRepository repository) {
        this.pagedResourcesAssembler = pagedResourcesAssembler;
        this.createClientPayment = createClientPayment;
        this.updateClientPayment = updateClientPayment;
        this.deleteClientPayment = deleteClientPayment;
        this.mapper = mapper;
        this.repository = repository;
    }

    @PostMapping
    public ClientPaymentResponse create(@RequestBody CreateClientPaymentRequest request){
        return createClientPayment.create(request);
    }

    @PatchMapping
    public ResponseEntity<ClientPaymentResponse> update(@RequestBody UpdateClientPaymentRequest request){
        return ResponseEntity.ok(updateClientPayment.update(request));
    }

    @DeleteMapping("/{id}")
    public ClientPaymentResponse delete(@PathVariable Long id){return deleteClientPayment.delete(id);}

    @GetMapping
    PagedModel<ClientPaymentResponse> get(SearchFilter filter, Pageable pageable){
        final List<SearchCriteria> criteriaList = CriteriaArrayToList.getCriteriaList(filter);
        final FilterSpecifications<ClientPayment> specifications = new FilterSpecifications<>(criteriaList);
        final Page<org.example.model.ClientPayment> page = repository.findAll(specifications, pageable).map(mapper::entityToDomain);
        return pagedResourcesAssembler.toModel(page);
    }

    @GetMapping("/all")
    public List<org.example.model.ClientPayment> getAll(){
        return repository.findAll().stream().map(mapper::entityToDomain).toList();
    }
}
