package org.example.controllers;

import org.example.contract.request.CreateTransferMaterialRequest;
import org.example.contract.response.TransferMaterialResponse;
import org.example.useCases.CreateTransferMaterial;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/transfer-materials")
public class TransferMaterialController {

    private final CreateTransferMaterial createTransferMaterial;

    public TransferMaterialController(CreateTransferMaterial createTransferMaterial) {
        this.createTransferMaterial = createTransferMaterial;
    }

    @PostMapping
    public TransferMaterialResponse createTransferMaterial(@RequestBody CreateTransferMaterialRequest request){
        return createTransferMaterial.execute(request);
    }
}
