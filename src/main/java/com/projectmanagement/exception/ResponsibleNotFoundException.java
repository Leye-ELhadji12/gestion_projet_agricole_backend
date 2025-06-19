package com.projectmanagement.exception;

import java.io.Serial;

public class ResponsibleNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    public ResponsibleNotFoundException(String message) {
        super(message);
    }
}
