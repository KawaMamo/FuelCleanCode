package org.example.useCases.create;

import org.example.contract.repository.GroupRepo;
import org.example.contract.request.create.CreateGroupRequest;
import org.example.contract.response.GroupResponse;
import org.example.mappers.GroupDomainMapper;
import org.example.model.Group;
import org.example.validators.create.CreateGroupValidator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CreateGroup {
    private final CreateGroupValidator validator;
    private final GroupDomainMapper mapper;
    private final GroupRepo groupRepo;

    public CreateGroup(CreateGroupValidator validator, GroupDomainMapper mapper, GroupRepo groupRepo) {
        this.validator = validator;
        this.mapper = mapper;
        this.groupRepo = groupRepo;
    }

    public GroupResponse execute(CreateGroupRequest request){
        validator.validate(request);
        final Group group = mapper.requestToDomain(request);
        group.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        final Group save = groupRepo.save(group);
        return mapper.domainToResponse(save);
    }
}
