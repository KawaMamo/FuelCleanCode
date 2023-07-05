package org.example.controllers;

import org.example.contract.request.CreateOfficeRequest;
import org.example.contract.response.OfficeResponse;
import org.example.useCases.CreateOffice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/office")
public class OfficeController {

    private final CreateOffice createOffice;

    public OfficeController(CreateOffice createOffice) {
        this.createOffice = createOffice;
    }
    @PostMapping
    public OfficeResponse createOffice(@RequestBody CreateOfficeRequest request){
        return createOffice.execute(request);
    }
}
