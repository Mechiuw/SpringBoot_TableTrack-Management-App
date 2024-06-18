package com.tabletrack.table_track_API.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ImportValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD,ElementType.ANNOTATION_TYPE,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidImportRequest {
    String message() default "Invalid Import Request";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
