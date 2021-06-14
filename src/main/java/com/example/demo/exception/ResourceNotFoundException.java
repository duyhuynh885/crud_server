package com.example.demo.exception;


/**
 * ResourceNotFoundException
 * Version 1.0
 * Date: 07-06-2021
 * Copyright
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 07-06-2021           DUYHV9            Create
 */
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * ResourceNotFoundException
     *
     * @param msg String
     */
    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
