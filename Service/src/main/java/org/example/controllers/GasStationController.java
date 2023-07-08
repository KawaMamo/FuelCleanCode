package org.example.controllers;

import org.example.contract.request.CreateGasStationRequest;
import org.example.contract.response.GasStationResponse;
import org.example.useCases.CreateGasStation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/gas-station")
public class GasStationController {
    private final CreateGasStation createGasStation;


    public GasStationController(CreateGasStation createGasStation) {
        this.createGasStation = createGasStation;
    }

    @PostMapping
    public GasStationResponse createGasStation(@RequestBody CreateGasStationRequest request){
        return createGasStation.execute(request);
    }
}
