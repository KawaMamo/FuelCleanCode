package org.example.adapters;

import org.example.contract.repository.GroupRepo;
import org.example.mappers.GroupMapper;
import org.example.model.Group;
import org.example.repositories.GroupRepository;
import org.example.entities.GroupEntity;

import java.time.LocalDateTime;
import java.util.Optional;

public class GroupAdapter implements GroupRepo {
    private final GroupRepository groupRepository;
    private final GroupMapper mapper;

    public GroupAdapter(GroupRepository groupRepository, GroupMapper mapper) {
        this.groupRepository = groupRepository;
        this.mapper = mapper;
    }

    @Override
    public Group save(Group group) {
        final GroupEntity groupEntity = mapper.domainToEntity(group);
        groupEntity.setCreatedAt(LocalDateTime.now());
        final GroupEntity save = groupRepository.save(groupEntity);
        return mapper.entityToDomain(save);
    }

    @Override
    public Optional<Group> findById(Long id) {
        final Optional<GroupEntity> byId = groupRepository.findById(id);
        final GroupEntity groupEntity = byId.orElse(null);
        final Group group = mapper.entityToDomain(groupEntity);
        return Optional.ofNullable(group);
    }
}
