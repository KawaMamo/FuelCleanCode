package org.example.contract.repository;

import org.example.model.PaidToParent;

import java.util.Optional;

public interface PaidToParentRepo {
    PaidToParent save(PaidToParent paidToParent);
    Optional<PaidToParent> findById(Long id);
    void delete(PaidToParent paidToParent);
}
