package com.appointment.manage.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private String path;
    private String message;
    private String description;
    private List<ValidationError> errors;

    @Getter
    @Setter
    @RequiredArgsConstructor
    private static class ValidationError {
        private final String field;
        private final String message;
    }

    public void addValidationError(String field, String message){
        if(Objects.isNull(errors)){
            errors = new ArrayList<>();
        }
        errors.add(new ValidationError(field, message));
    }
}
