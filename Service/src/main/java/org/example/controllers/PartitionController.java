package org.example.controllers;

import org.example.contract.request.create.CreatePartitionRequest;
import org.example.contract.request.update.UpdatePartitionRequest;
import org.example.contract.response.PartitionResponse;
import org.example.entities.PartitionEntity;
import org.example.mappers.PartitionMapper;
import org.example.model.Partition;
import org.example.repositories.PartitionRepository;
import org.example.specifications.CriteriaArrayToList;
import org.example.specifications.FilterSpecifications;
import org.example.specifications.SearchCriteria;
import org.example.specifications.SearchFilter;
import org.example.useCases.create.CreatePartition;
import org.example.useCases.delete.DeletePartition;
import org.example.useCases.update.UpdatePartition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/partition")
public class PartitionController {

    private final CreatePartition createPartition;
    private final PartitionRepository partitionRepository;
    private final PartitionMapper partitionMapper;
    private final PagedResourcesAssembler assembler;
    private final UpdatePartition updatePartition;
    private final DeletePartition deletePartition;

    public PartitionController(CreatePartition createPartition,
                               PartitionRepository partitionRepository,
                               PartitionMapper partitionMapper,
                               PagedResourcesAssembler assembler,
                               UpdatePartition updatePartition, DeletePartition deletePartition) {
        this.createPartition = createPartition;
        this.partitionRepository = partitionRepository;
        this.partitionMapper = partitionMapper;
        this.assembler = assembler;
        this.updatePartition = updatePartition;
        this.deletePartition = deletePartition;
    }

    @PostMapping
    public PartitionResponse createPartition(@RequestBody CreatePartitionRequest request){
        return createPartition.execute(request);
    }

    @GetMapping
    PagedModel<PartitionResponse> listPartitions(SearchFilter filter, Pageable pageable){
        final List<SearchCriteria> criteriaList = CriteriaArrayToList.getCriteriaList(filter);
        final FilterSpecifications<PartitionEntity> specifications = new FilterSpecifications<>(criteriaList);
        final Page<Partition> page = partitionRepository.findAll(specifications, pageable).map(partitionMapper::entityToDomain);
        return assembler.toModel(page);
    }

    @PatchMapping
    ResponseEntity<PartitionResponse> updatePartition(@RequestBody UpdatePartitionRequest request){
        return ResponseEntity.ok(updatePartition.execute(request));
    }
    @DeleteMapping("/{id}")
    public PartitionResponse delete(@PathVariable Long id){
        return deletePartition.execute(id);
    }
}
