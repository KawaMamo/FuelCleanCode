package org.example.useCases.delete;

import org.example.contract.repository.TransRepo;
import org.example.contract.response.TransResponse;
import org.example.mappers.DomainTransMapper;
import org.example.model.Transportation;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;
import java.util.Objects;

public class DeleteTrans {
    private final TransRepo transRepo;
    private final DomainTransMapper mapper;

    public DeleteTrans(TransRepo transRepo, DomainTransMapper mapper) {
        this.transRepo = transRepo;
        this.mapper = mapper;
    }

    public TransResponse execute(Long id) {
        final Transportation transportation = transRepo.findByIdAndDeletedAt(id, null).orElseThrow(NoSuchElementException::new);
        if(transportation.getPartitions().size()==0 && transportation.getTransLogs().size()==0){
            transportation.setDeletedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
            final Transportation save = transRepo.save(transportation);
            return mapper.toResponse(save);
        } else
            throw new RuntimeException("delete Child Entities first");

    }
}
