package org.example.controllers;

import org.example.contract.request.CreateTransRequest;
import org.example.contract.response.CreateTransResponse;
import org.example.useCases.CreateTrans;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/trans")
public class TransController {

    private final CreateTrans createTrans;

    public TransController(CreateTrans createTrans) {
        this.createTrans = createTrans;
    }

    @PostMapping
    public CreateTransResponse createTrans(@RequestBody CreateTransRequest request) {
        return createTrans.execute(request);
    }
}
