package org.example.controllers;

import org.example.contract.request.CreateTransLineRequest;
import org.example.contract.response.TransLineResponse;
import org.example.useCases.CreateTransLine;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("app/v1/trans-line")
public class TransLineController {

    private final CreateTransLine createTransLine;

    public TransLineController(CreateTransLine createTransLine) {
        this.createTransLine = createTransLine;
    }
    @PostMapping
    public TransLineResponse execute(@RequestBody CreateTransLineRequest request){
        return createTransLine.execute(request);
    }
}
