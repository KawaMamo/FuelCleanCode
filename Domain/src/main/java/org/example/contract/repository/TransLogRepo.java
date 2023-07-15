package org.example.contract.repository;

import org.example.model.TransLog;

import java.util.Optional;

public interface TransLogRepo {
    TransLog save(TransLog transLog);
    Optional<TransLog> findById(Long id);
}
