package org.example.controllers;

import org.example.contract.request.CreateMaterialRequest;
import org.example.contract.response.MaterialResponse;
import org.example.useCases.CreateMaterial;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/material")
public class MaterialController {

    private final CreateMaterial createMaterial;

    public MaterialController(CreateMaterial createMaterial) {
        this.createMaterial = createMaterial;
    }

    @PostMapping
    MaterialResponse createMaterial(CreateMaterialRequest request){
        return createMaterial.execute(request);
    }
}
