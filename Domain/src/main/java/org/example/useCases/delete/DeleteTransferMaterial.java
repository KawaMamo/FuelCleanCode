package org.example.useCases.delete;

import org.example.contract.repository.TransferMaterialRepo;
import org.example.contract.response.TransferMaterialResponse;
import org.example.mappers.TransferMaterialDomainMapper;
import org.example.model.TransferMaterials;

import java.util.NoSuchElementException;

public class DeleteTransferMaterial {
    private final TransferMaterialDomainMapper mapper;
    private final TransferMaterialRepo repo;

    public DeleteTransferMaterial(TransferMaterialDomainMapper mapper, TransferMaterialRepo repo) {
        this.mapper = mapper;
        this.repo = repo;
    }

    public TransferMaterialResponse execute(Long id){
        final TransferMaterials transferMaterials = repo.findById(id).orElseThrow(NoSuchElementException::new);
        repo.delete(transferMaterials);
        return mapper.domainToResponse(transferMaterials);
    }
}
