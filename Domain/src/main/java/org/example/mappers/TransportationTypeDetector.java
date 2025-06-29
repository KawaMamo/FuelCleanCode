package org.example.mappers;

import org.example.contract.repository.TransRepo;
import org.example.contract.repository.TransferMaterialRepo;
import org.example.model.TransferMaterials;
import org.example.model.Transportation;
import org.example.model.TransportationReason;

import java.util.Optional;

public class TransportationTypeDetector {
    private final TransRepo transRepo;
    private final TransferMaterialRepo transferMaterialRepo;

    public TransportationTypeDetector(TransRepo transRepo, TransferMaterialRepo transferMaterialRepo) {
        this.transRepo = transRepo;
        this.transferMaterialRepo = transferMaterialRepo;
    }

    public TransportationReason detect(Long requestId){
        try {
            final Optional<Transportation> byId = transRepo.findByIdAndDeletedAt(requestId, null);
            if(byId.isPresent())
                return byId.get();
        }catch (Exception e) {}

        try {
            final Optional<TransferMaterials> byId = transferMaterialRepo.findById(requestId);
            if(byId.isPresent())
                return byId.get();
        }catch (Exception e){}
        return null;
    }
}
