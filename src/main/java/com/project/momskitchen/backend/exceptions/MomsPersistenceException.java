package com.project.momskitchen.backend.exceptions;


public class MomsPersistenceException extends Exception{
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public MomsPersistenceException(String message) {
        super(message);
    }

    public MomsPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
