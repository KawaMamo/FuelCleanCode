package org.example.controllers;

import org.example.contract.request.CreateTrafficCenterRequest;
import org.example.contract.response.TrafficCenterResponse;
import org.example.useCases.CreateTrafficCenter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/traffic-center")
public class TrafficCenterController {

    private final CreateTrafficCenter createTrafficCenter;

    public TrafficCenterController(CreateTrafficCenter createTrafficCenter) {
        this.createTrafficCenter = createTrafficCenter;
    }
    @PostMapping
    TrafficCenterResponse createTrafficCenter(@RequestBody CreateTrafficCenterRequest request){
        return createTrafficCenter.execute(request);
    }


}
