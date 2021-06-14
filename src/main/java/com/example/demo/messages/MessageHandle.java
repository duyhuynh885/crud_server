package com.example.demo.messages;


/**
 * MessageHandle
 * Version 1.0
 * Date: 07-06-2021
 * Copyright
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 07-06-2021           DUYHV9            Create
 */
public class MessageHandle {

    //AUTH MESSAGE
    public static final String ROLE_NOT_FOUND = "Role is not found.";
    public static final String USERNAME_TAKEN = "Username is already taken!";
    public static final String EMAIL_TAKEN = "Email is already in use!";
    public static final String SIGN_IN_FAIL = "Invalid username or password!";
    public static final String USER_NOTFOUND_WITH = "Invalid username or password!";
    public static final String USER_DELETE = "Username is deleted by admin, Please contact admin@gmail.com!";
    public static final String SIGNUP_SUCCESS = "User registered successfully!";
    public static final String INVALID_MAIL = "Email invalid!";
    public static final String SEND_MAIL_SUCCESS = "We have sent a reset password link to your email. Please check.";
    public static final String SEND_MAIL_ERROR = "Error while sending email";
    public static final String INVALID_TOKEN = "Token invalid!";
    public static final String NOTFOUND_USER = "Could not find any user with the email ";
    public static final String RESET_PASS_SUCCESS = "You have successfully changed your password.";

    //CATEGORY MESSAGE
    public static final String CATEGORY_404 = "Category is not exist in database!";
    public static final String CATEGORY_IS_DUPLICATE = "Category is duplicate!";
    //BRAND MESSAGE
    public static final String BRAND_404 = "Brand is not exist in database!";
    public static final String BRAND_IS_DUPLICATE = "Brand is duplicate!";
    //PRODUCT MESSAGE
    public static final String PRODUCT_SEARCH_404 = "Product Name Not Found!";
    public static final String PRODUCT_NAME_DUP = "Product name duplicate!";
    public static final String PRODUCT_404 = "Product is not exist in database!";

    //STOCK MESSAGE
    public static final String STOCK_404_404 = "Stock is not exist in database!";
    //USER MESSAGE
    public static final String USER_404 = "Users is not exist in database!";

    //DB MESSAGE
    public static final String DB_FOREIN_KEY_ERROR = "Foreign key constraint failed!";
    public static final String DB_NORECORD = "No record in Database!";

    //JWT MESSAGE
    public static final String JWT_ERROR_1 = "Invalid JWT signature";
    public static final String JWT_ERROR_2 = "Invalid JWT token!";
    public static final String JWT_ERROR_3 = "JWT token is expired!";
    public static final String JWT_ERROR_4 = "JWT token is unsupported";
    public static final String JWT_ERROR_5 = "JWT claims string is empty";

    //SYSTEM MESSAGE
    public static final String SYSTEM_ERROR = "System Error!";

}
