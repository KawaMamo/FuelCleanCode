package org.example.controllers;

import org.example.contract.request.create.CreateTransLogRequest;
import org.example.contract.response.TransLogResponse;
import org.example.entities.TransLogEntity;
import org.example.mappers.TransLogMapper;
import org.example.model.TransLog;
import org.example.repositories.TransLogRepository;
import org.example.specifications.CriteriaArrayToList;
import org.example.specifications.FilterSpecifications;
import org.example.specifications.SearchCriteria;
import org.example.specifications.SearchFilter;
import org.example.useCases.create.CreateTransLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/trans-log")
public class TransLogController {

    private final CreateTransLog createTransLog;
    private final TransLogRepository transLogRepository;
    private final TransLogMapper transLogMapper;
    private final PagedResourcesAssembler assembler;

    public TransLogController(CreateTransLog createTransLog,
                              TransLogRepository transLogRepository,
                              TransLogMapper transLogMapper,
                              PagedResourcesAssembler assembler) {
        this.createTransLog = createTransLog;
        this.transLogRepository = transLogRepository;
        this.transLogMapper = transLogMapper;
        this.assembler = assembler;
    }

    @PostMapping
    public TransLogResponse createTransLog(@RequestBody CreateTransLogRequest request){
        return createTransLog.execute(request);
    }

    @GetMapping
    public PagedModel<TransLogResponse> listTransLogs(SearchFilter filter, Pageable pageable){
        final List<SearchCriteria> criteriaList = CriteriaArrayToList.getCriteriaList(filter);
        final FilterSpecifications<TransLogEntity> specifications = new FilterSpecifications<>(criteriaList);
        final Page<TransLog> page = transLogRepository.findAll(specifications, pageable).map(transLogMapper::entityToDomain);
        return assembler.toModel(page);
    }
}
