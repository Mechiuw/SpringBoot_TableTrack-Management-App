package com.bahari.bahari_resto_API.utils;

import com.bahari.bahari_resto_API.model.dto.request.ImportRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class ImportValidator extends ConstraintValidator<ValidImportRequest, ImportRequest> {

    @Override
    public void initialize(ValidImportRequest constraintAnnotation) {
    }

    @Override
    public boolean isValid(ImportRequest importRequest, ConstraintValidatorContext context) {
        if(importRequest.getStoreId() == null){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Store ID is Required")
                    .addConstraintViolation();
            return false;
        }
        if(importRequest.getWarehouseId() == null){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Warehouse ID is Required")
                    .addConstraintViolation();
            return false;
        }
        if(importRequest.getImportDetailsRequests().stream().findAny().isEmpty()){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Import Details are empty")
                    .addConstraintViolation();
            return false;
        }
        if(importRequest.getContainerRequestList().stream().findAny().isEmpty()){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Containers are Empty")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }

}
