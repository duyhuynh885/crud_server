package com.example.demo.payload.request;

import javax.validation.constraints.NotBlank;

/**
 * ChangePasswordRequestPayload
 * Version 1.0
 * Date: 07-06-2021
 * Copyright
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 07-06-2021           DUYHV9            Create
 */
public class ChangePasswordRequest {

    @NotBlank
    private String oldPassword;

    @NotBlank
    private String newPassword;

    /**
     * getOldPassword
     *
     * @return oldPassword
     */
    public String getOldPassword() {
        return oldPassword;
    }

    /**
     * setOldPassword
     *
     * @param oldPassword String
     */
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    /**
     * getNewPassword
     *
     * @return newPassword
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * setNewPassword
     *
     * @param newPassword String
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
