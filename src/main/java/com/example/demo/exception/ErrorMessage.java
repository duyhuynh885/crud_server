package com.example.demo.exception;

import java.util.Date;


/**
 * ErrorMessage
 * Version 1.0
 * Date: 07-06-2021
 * Copyright
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 07-06-2021           DUYHV9            Create
 */
public class ErrorMessage {

    private final int statusCode;
    private final Date timestamp;
    private final String message;
    private final String description;

    /**
     * @param statusCode  int
     * @param timestamp   Date
     * @param message     String
     * @param description String
     */
    public ErrorMessage(int statusCode, Date timestamp, String message, String description) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }

    /**
     * getStatusCode
     *
     * @return statusCode
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * getTimestamp
     *
     * @return timestamp
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * getMessage
     *
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * getDescription
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }
}
