package org.example.controllers;

import org.example.contract.request.create.CreateGroupRequest;
import org.example.contract.request.update.UpdateGroupRequest;
import org.example.contract.response.GroupResponse;
import org.example.entities.GroupEntity;
import org.example.mappers.GroupMapper;
import org.example.model.Group;
import org.example.repositories.GroupRepository;
import org.example.specifications.CriteriaArrayToList;
import org.example.specifications.FilterSpecifications;
import org.example.specifications.SearchCriteria;
import org.example.specifications.SearchFilter;
import org.example.useCases.create.CreateGroup;
import org.example.useCases.delete.DeleteGroup;
import org.example.useCases.update.UpdateGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/group")
public class GroupController {
    private final CreateGroup createGroup;
    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final PagedResourcesAssembler pagedResourcesAssembler;
    private final UpdateGroup updateGroup;
    private final DeleteGroup deleteGroup;

    public GroupController(CreateGroup createGroup,
                           GroupRepository groupRepository,
                           GroupMapper groupMapper,
                           PagedResourcesAssembler pagedResourcesAssembler,
                           UpdateGroup updateGroup, DeleteGroup deleteGroup) {
        this.createGroup = createGroup;
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
        this.updateGroup = updateGroup;
        this.deleteGroup = deleteGroup;
    }

    @PostMapping
    public GroupResponse createGroup(@RequestBody CreateGroupRequest request){
        return createGroup.execute(request);
    }

    @GetMapping
    public PagedModel<GroupResponse> listGroups(SearchFilter filter, Pageable pageable) {
        final List<SearchCriteria> criteriaList = CriteriaArrayToList.getCriteriaList(filter);
        final FilterSpecifications<GroupEntity> specifications = new FilterSpecifications<>(criteriaList);
        final Page<Group> page = groupRepository.findAll(specifications, pageable).map(groupMapper::entityToDomain);
        return pagedResourcesAssembler.toModel(page);
    }

    @GetMapping("/all")
    public List<Group> listAllGroups(){
        return groupRepository.findAll().stream().map(groupMapper::entityToDomain).toList();
    }

    @PatchMapping
    public ResponseEntity<GroupResponse> updateGroup(@RequestBody UpdateGroupRequest request){
        return ResponseEntity.ok(updateGroup.execute(request));
    }
    @DeleteMapping("/{id}")
    public GroupResponse delete(@PathVariable Long id) {
        return deleteGroup.execute(id);
    }
}
