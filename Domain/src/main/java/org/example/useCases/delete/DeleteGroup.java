package org.example.useCases.delete;


import org.example.contract.repository.GroupRepo;
import org.example.contract.response.GroupResponse;
import org.example.mappers.GroupDomainMapper;
import org.example.model.Group;

import java.util.NoSuchElementException;

public class DeleteGroup {
    private final GroupRepo repo;
    private final GroupDomainMapper mapper;
    public DeleteGroup(GroupRepo repo, GroupDomainMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }
    public GroupResponse execute(Long id){
        final Group group = repo.findById(id).orElseThrow(NoSuchElementException::new);
        repo.delete(group);
        return mapper.domainToResponse(group);
    }
}
