package org.example.controllers;

import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/client-payment")
public class ClientPaymentController {

    private final PagedResourcesAssembler pagedResourcesAssembler;

    public ClientPaymentController(PagedResourcesAssembler pagedResourcesAssembler) {
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }
}
