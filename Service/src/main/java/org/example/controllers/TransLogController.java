package org.example.controllers;

import org.example.contract.request.CreateTransLogRequest;
import org.example.contract.response.TransLogResponse;
import org.example.useCases.CreateTransLog;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/trans-log")
public class TransLogController {

    private final CreateTransLog createTransLog;

    public TransLogController(CreateTransLog createTransLog) {
        this.createTransLog = createTransLog;
    }

    @PostMapping
    public TransLogResponse createTransLog(@RequestBody CreateTransLogRequest request){
        return createTransLog.execute(request);
    }
}
