package org.example.repositories.adapters;

import org.example.contract.repository.OfficeRepo;
import org.example.mappers.OfficeMapper;
import org.example.model.Office;
import org.example.repositories.OfficeRepository;
import org.example.repositories.entities.OfficeEntity;

public class OfficeAdapter implements OfficeRepo {

    private final OfficeRepository repository;
    private final OfficeMapper mapper;

    public OfficeAdapter(OfficeRepository repository, OfficeMapper mapper) {

        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Office save(Office office) {
        final OfficeEntity officeEntity = mapper.domainToEntity(office);
        final OfficeEntity save = repository.save(officeEntity);
        return mapper.entityToDomain(save);
    }
}
