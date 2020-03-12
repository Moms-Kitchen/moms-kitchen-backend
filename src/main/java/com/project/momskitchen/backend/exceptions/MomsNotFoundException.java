package com.project.momskitchen.backend.exceptions;

public class MomsNotFoundException extends Exception{
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public MomsNotFoundException(String message) {
        super(message);
    }

    public MomsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
}