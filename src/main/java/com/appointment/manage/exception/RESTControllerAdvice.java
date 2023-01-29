package com.appointment.manage.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
public class RESTControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<Object> handleRecordNotFoundException(RecordNotFoundException ex, WebRequest request) {
        String path = ((ServletWebRequest)request).getRequest().getRequestURI();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().path(path)
                .message(ex.getMessage()).description(ex.getDescription()).build());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                                  final HttpHeaders headers, final HttpStatus status,
                                                                  final WebRequest request) {
        String path = ((ServletWebRequest)request).getRequest().getRequestURI();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(buildValidationErrors(ex.getFieldErrors(), HttpStatus.BAD_REQUEST.name(), path));
    }

    private ErrorResponse  buildValidationErrors(
            List<FieldError> violations, String message, String path) {
        var errorResponse = ErrorResponse.builder().message(message)
                .path(path)
                .build();
        violations.forEach(constraintViolation -> {
            errorResponse.addValidationError(constraintViolation.getField(), constraintViolation.getDefaultMessage());
        });
       return errorResponse;
    }

}
