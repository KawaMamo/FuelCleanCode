package org.example.validators.create;

import org.example.contract.repository.GasStationRepo;
import org.example.contract.repository.MaterialRepo;
import org.example.contract.repository.TransRepo;
import org.example.contract.request.create.CreatePartitionRequest;
import org.example.exceptions.DomainValidationException;
import org.example.exceptions.ValidationErrorDetails;
import org.example.model.Partition;
import org.example.validators.update.ExceptionThrower;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static org.example.contract.constant.DomainConstant.*;

public class CreatePartitionValidator {
    private final MaterialRepo materialRepo;
    private final GasStationRepo gasStationRepo;
    private final TransRepo transRepo;

    public CreatePartitionValidator(MaterialRepo materialRepo, GasStationRepo gasStationRepo, TransRepo transRepo) {
        this.materialRepo = materialRepo;
        this.gasStationRepo = gasStationRepo;
        this.transRepo = transRepo;
    }

    public void validate(CreatePartitionRequest request){
        Set<ValidationErrorDetails> errorDetails = new HashSet<>();

        transRepo.findByIdAndDeletedAt(request.getTransportationId(), null)
                .ifPresent(transportation -> {
                    final Optional<Partition> reduce = transportation.getPartitions().stream()
                            .reduce((partition, partition2) -> {
                                final Partition partition1 = new Partition();
                                partition1.setAmount(partition.getAmount() + partition2.getAmount());
                                return partition1;
                            });
                    if(reduce.isPresent()) {
                        if(reduce.get().getAmount()+request.getAmount() > transportation.getSize()) {
                            errorDetails.add(new ValidationErrorDetails(AMOUNT_FIELD, ILLEGAL_VALUE));
                        }
                    }
                });

        if (Objects.isNull(request.getAmount())) {
            errorDetails.add(new ValidationErrorDetails(AMOUNT_FIELD, NULL_ERROR_MSG));
        }

        if(materialRepo.findById(request.getMaterialId()).isEmpty()){
            errorDetails.add(new ValidationErrorDetails(MATERIAL_FIELD, ELEMENT_NOT_FOUND));
        }

        if(gasStationRepo.findById(request.getGasStationId()).isEmpty()){
            errorDetails.add(new ValidationErrorDetails(GAS_STATION_FIELD, ELEMENT_NOT_FOUND));
        }

        if(transRepo.findByIdAndDeletedAt(request.getTransportationId(), null).isEmpty()){
            errorDetails.add(new ValidationErrorDetails(TRANS_FIELD, ELEMENT_NOT_FOUND));
        }

        ExceptionThrower.throwIfNotEmpty(errorDetails);
    }
}
