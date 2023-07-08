package org.example.contract.repository;

import org.example.model.Group;

import java.util.Optional;

public interface GroupRepo {
    Group save(Group group);
    Optional<Group> findById(Long id);
}
