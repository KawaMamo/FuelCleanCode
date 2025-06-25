package org.example.adapters;

import org.example.contract.repository.DeletedRepo;
import org.example.entities.DeletedEntity;
import org.example.mappers.DeletedMapper;
import org.example.model.Deleted;
import org.example.repositories.DeletedRepository;

import java.security.Principal;
import java.util.Optional;

public class DeletedAdapter implements DeletedRepo {
    private final DeletedRepository deletedRepository;
    private final DeletedMapper mapper;

    public DeletedAdapter(DeletedRepository deletedRepository, DeletedMapper mapper) {
        this.deletedRepository = deletedRepository;
        this.mapper = mapper;
    }

    @Override
    public Deleted save(Deleted deleted) {
        final DeletedEntity deletedEntity = mapper.domainToEntity(deleted);
        final DeletedEntity save = deletedRepository.save(deletedEntity);
        return mapper.entityToDomain(save);
    }

    @Override
    public Optional<Deleted> findById(Long id) {
        final Optional<DeletedEntity> byId = deletedRepository.findById(id);
        final Deleted deleted = mapper.entityToDomain(byId.orElse(null));
        return Optional.ofNullable(deleted);
    }

    @Override
    public void delete(Deleted deleted) {
        deletedRepository.deleteById(deleted.getId());
    }
}
