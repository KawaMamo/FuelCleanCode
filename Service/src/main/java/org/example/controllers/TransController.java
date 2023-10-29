package org.example.controllers;

import org.example.contract.request.create.CreateTransRequest;
import org.example.contract.request.update.UpdateTransRequest;
import org.example.contract.response.TransResponse;
import org.example.entities.TransportationEntity;
import org.example.mappers.TransMapper;
import org.example.model.Transportation;
import org.example.repositories.TransRepoJpa;
import org.example.specifications.*;
import org.example.useCases.create.CreateTrans;
import org.example.useCases.delete.DeleteTrans;
import org.example.useCases.update.UpdateTrans;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/trans")
public class TransController {

    private final CreateTrans createTrans;
    private final TransRepoJpa transRepoJpa;
    private final PagedResourcesAssembler pagedResourcesAssembler;
    private final TransMapper transMapper;
    private final UpdateTrans updateTrans;
    private final DeleteTrans deleteTrans;

    public TransController(CreateTrans createTrans,
                           TransRepoJpa transRepoJpa,
                           PagedResourcesAssembler pagedResourcesAssembler,
                           TransMapper transMapper, UpdateTrans updateTrans, DeleteTrans deleteTrans) {
        this.createTrans = createTrans;
        this.transRepoJpa = transRepoJpa;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
        this.transMapper = transMapper;
        this.updateTrans = updateTrans;
        this.deleteTrans = deleteTrans;
    }

    @PostMapping
    public TransResponse createTrans(@RequestBody CreateTransRequest request) {
        return createTrans.execute(request);
    }

    @PostMapping("/listTrans")
    public PagedModel<TransResponse> listTrans(@RequestBody TransSpecifications specifications, Pageable pageable){
        final Page<Transportation> map = transRepoJpa.findAll(specifications, pageable).map(transMapper::entityToDomain);
        return pagedResourcesAssembler.toModel(map);
    }

    @GetMapping
    public PagedModel<TransResponse> getTrans(SearchFilter criteria, Pageable pageable){
        List<SearchCriteria> searchCriteria = CriteriaArrayToList.getCriteriaList(criteria);
        final FilterSpecifications<TransportationEntity> specifications = new FilterSpecifications<>(searchCriteria);
        final Page<Transportation> map = transRepoJpa.findAll(specifications, pageable).map(transMapper::entityToDomain);
        return pagedResourcesAssembler.toModel(map);
    }

    @GetMapping("/all")
    public List<Transportation> getAll(){
        return transRepoJpa.findAll().stream().map(transMapper::entityToDomain).toList();
    }

    @PatchMapping
    public ResponseEntity<TransResponse> updateTrans(@RequestBody UpdateTransRequest request){
        return ResponseEntity.ok(updateTrans.execute(request));
    }
    @DeleteMapping("/{id}")
    public TransResponse deleteTrans(@PathVariable Long id){
        return deleteTrans.execute(id);
    }

}
