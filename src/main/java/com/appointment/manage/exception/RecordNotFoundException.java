package com.appointment.manage.exception;

import lombok.Getter;

@Getter
public class RecordNotFoundException extends RuntimeException {
    private String description;
    public RecordNotFoundException(String message, String description) {
        super(message);
        this.description = description;
    }
}
