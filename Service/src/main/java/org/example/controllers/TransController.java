package org.example.controllers;

import org.example.contract.request.CreateTransRequest;
import org.example.contract.response.CreateTransResponse;
import org.example.entities.TransportationEntity;
import org.example.mappers.TransMapper;
import org.example.model.Transportation;
import org.example.repositories.TransRepoJpa;
import org.example.specifications.TransSpecifications;
import org.example.useCases.CreateTrans;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/trans")
public class TransController {

    private final CreateTrans createTrans;
    private final TransRepoJpa transRepoJpa;
    private final PagedResourcesAssembler pagedResourcesAssembler;
    private final TransMapper transMapper;

    public TransController(CreateTrans createTrans,
                           TransRepoJpa transRepoJpa,
                           PagedResourcesAssembler pagedResourcesAssembler,
                           TransMapper transMapper) {
        this.createTrans = createTrans;
        this.transRepoJpa = transRepoJpa;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
        this.transMapper = transMapper;
    }

    @PostMapping
    public CreateTransResponse createTrans(@RequestBody CreateTransRequest request) {
        return createTrans.execute(request);
    }

    @PostMapping("/listTrans")
    public PagedModel<CreateTransResponse> listTrans(@RequestBody TransSpecifications specifications,Pageable pageable){
        final Page<Transportation> map = transRepoJpa.findAll(specifications, pageable).map(transMapper::entityToDomain);
        return pagedResourcesAssembler.toModel(map);
    }
}
