package org.example.useCases.update;

import org.example.contract.repository.GroupRepo;
import org.example.contract.request.update.UpdateGroupRequest;
import org.example.contract.response.GroupResponse;
import org.example.mappers.GroupDomainMapper;
import org.example.model.Group;
import org.example.validators.update.UpdateGroupValidator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;

public class UpdateGroup {

    private final UpdateGroupValidator validator;
    private final GroupDomainMapper mapper;
    private final GroupRepo groupRepo;

    public UpdateGroup(UpdateGroupValidator validator, GroupDomainMapper mapper, GroupRepo groupRepo) {
        this.validator = validator;
        this.mapper = mapper;
        this.groupRepo = groupRepo;
    }

    public GroupResponse execute(UpdateGroupRequest request){
        final Group original = groupRepo.findById(request.getId()).orElseThrow(NoSuchElementException::new);
        validator.validate(request);
        final Group group = mapper.requestToDomain(request);
        group.setCreatedAt(original.getCreatedAt());
        group.setUpdatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        final Group save = groupRepo.save(group);
        return mapper.domainToResponse(save);
    }
}
