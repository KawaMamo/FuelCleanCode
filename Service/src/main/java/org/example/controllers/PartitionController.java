package org.example.controllers;

import org.example.contract.request.CreatePartitionRequest;
import org.example.contract.response.PartitionResponse;
import org.example.useCases.CreatePartition;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/partition")
public class PartitionController {

    private final CreatePartition createPartition;

    public PartitionController(CreatePartition createPartition) {
        this.createPartition = createPartition;
    }

    @PostMapping
    public PartitionResponse createPartition(@RequestBody CreatePartitionRequest request){
        return createPartition.execute(request);
    }
}
