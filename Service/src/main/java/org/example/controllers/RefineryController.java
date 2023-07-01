package org.example.controllers;


import org.example.contract.request.CreateRefineryRequest;
import org.example.contract.response.CreateRefineryResponse;
import org.example.useCases.CreateRefinery;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/refinery")
public class RefineryController {

    private final CreateRefinery createRefinery;

    public RefineryController(CreateRefinery createRefinery) {
        this.createRefinery = createRefinery;
    }

    @PostMapping
    public CreateRefineryResponse createRefinery(@RequestBody CreateRefineryRequest request){
        return createRefinery.execute(request);
    }
}
