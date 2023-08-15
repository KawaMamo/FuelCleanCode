package org.example.adapters;

import org.example.contract.repository.TransLogRepo;
import org.example.entities.TransLogEntity;
import org.example.mappers.TransLogMapper;
import org.example.model.TransLog;
import org.example.repositories.TransLogRepository;

import java.util.Optional;

public class TransLogAdapter implements TransLogRepo {

    private final TransLogRepository transLogRepository;
    private final TransLogMapper transLogMapper;

    public TransLogAdapter(TransLogRepository transLogRepository, TransLogMapper transLogMapper) {
        this.transLogRepository = transLogRepository;
        this.transLogMapper = transLogMapper;
    }

    @Override
    public TransLog save(TransLog transLog) {
        final TransLogEntity transLogEntity = transLogMapper.domainToEntity(transLog);
        final TransLogEntity save = transLogRepository.save(transLogEntity);
        return transLogMapper.entityToDomain(save);
    }

    @Override
    public Optional<TransLog> findById(Long id) {
        final Optional<TransLogEntity> byId = transLogRepository.findById(id);
        return Optional.ofNullable(transLogMapper.entityToDomain(byId.orElse(null)));
    }

    @Override
    public void delete(TransLog transLog) {
        transLogRepository.delete(transLogMapper.domainToEntity(transLog));
    }
}
