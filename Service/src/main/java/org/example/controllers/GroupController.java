package org.example.controllers;

import org.example.contract.request.CreateGroupRequest;
import org.example.contract.response.GroupResponse;
import org.example.useCases.CreateGroup;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/group")
public class GroupController {
    private final CreateGroup createGroup;

    public GroupController(CreateGroup createGroup) {
        this.createGroup = createGroup;
    }

    @PostMapping
    public GroupResponse createGroup(@RequestBody CreateGroupRequest request){
        return createGroup.execute(request);
    }
}
