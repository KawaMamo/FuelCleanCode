package org.example.controllers;

import org.example.contract.request.CreateVehicleRequest;
import org.example.contract.response.VehicleResponse;
import org.example.useCases.CreateVehicle;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/vehicle")
public class VehicleController {

    private final CreateVehicle createVehicle;

    public VehicleController(CreateVehicle createVehicle) {
        this.createVehicle = createVehicle;
    }

    @PostMapping
    public VehicleResponse createVehicle(@RequestBody CreateVehicleRequest request){
        return createVehicle.execute(request);
    }
}
