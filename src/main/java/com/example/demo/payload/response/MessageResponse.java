package com.example.demo.payload.response;


/**
 * MessageResponse
 * Version 1.0
 * Date: 07-06-2021
 * Copyright
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 07-06-2021           DUYHV9            Create
 */
public class MessageResponse {
    private String message;

    /**
     * MessageResponse
     *
     * @param message String
     */
    public MessageResponse(String message) {
        this.message = message;
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
     * setMessage
     *
     * @param message String
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
