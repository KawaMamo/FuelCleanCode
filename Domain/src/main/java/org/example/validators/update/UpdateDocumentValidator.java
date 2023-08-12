package org.example.validators.update;

import org.example.contract.constant.DomainConstant;
import org.example.contract.request.update.UpdateDocumentRequest;
import org.example.exceptions.ValidationErrorDetails;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.example.contract.constant.DomainConstant.*;
import static org.example.contract.constant.DomainConstant.NULL_ERROR_MSG;

public class UpdateDocumentValidator {
    public void validate(UpdateDocumentRequest request){
        Set<ValidationErrorDetails> errorDetails = new HashSet<>();

        if(Objects.isNull(request.getId())){
            errorDetails.add(new ValidationErrorDetails(ID_FIELD, NULL_ERROR_MSG));
        }

        if(Objects.isNull(request.getContent())){
            errorDetails.add(new ValidationErrorDetails(DomainConstant.DOCUMENT_CONTENT_FIELD, NULL_ERROR_MSG));
        }

        if (Objects.isNull(request.getFileName())) {
            errorDetails.add(new ValidationErrorDetails(NAME_FIELD, NULL_ERROR_MSG));
        }

        if(Objects.isNull(request.getType())){
            errorDetails.add(new ValidationErrorDetails(TYPE_FIELD, NULL_ERROR_MSG));
        }

        ExceptionThrower.throwIfNotEmpty(errorDetails);
    }
}
