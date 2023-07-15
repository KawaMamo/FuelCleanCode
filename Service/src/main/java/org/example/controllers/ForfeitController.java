package org.example.controllers;

import org.example.contract.request.CreateForfeitRequest;
import org.example.contract.response.ForfeitResponse;
import org.example.useCases.CreateForfeit;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/forfeit")
public class ForfeitController {

    private final CreateForfeit createForfeit;

    public ForfeitController(CreateForfeit createForfeit) {
        this.createForfeit = createForfeit;
    }

    @PostMapping
    public ForfeitResponse createForfeit(@RequestBody CreateForfeitRequest request){
        return createForfeit.execute(request);
    }
}
