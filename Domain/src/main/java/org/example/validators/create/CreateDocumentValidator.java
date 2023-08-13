package org.example.validators.create;

import org.example.contract.constant.DomainConstant;
import org.example.contract.request.create.CreateDocumentRequest;
import org.example.exceptions.DomainValidationException;
import org.example.exceptions.ValidationErrorDetails;
import org.example.validators.update.ExceptionThrower;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.example.contract.constant.DomainConstant.*;

public class CreateDocumentValidator {

    public void validate(CreateDocumentRequest request){
        Set<ValidationErrorDetails> errorDetails = new HashSet<>();

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
