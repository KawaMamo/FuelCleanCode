package org.example.controllers;

import org.example.contract.request.CreateRegionRequest;
import org.example.contract.response.RegionResponse;
import org.example.useCases.CreateRegion;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/region")
public class RegionController {
    private final CreateRegion createRegion;

    public RegionController(CreateRegion createRegion) {
        this.createRegion = createRegion;
    }

    @PostMapping
    public RegionResponse createRegion(@RequestBody CreateRegionRequest request){
        return createRegion.execute(request);
    }
}
