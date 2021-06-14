package com.example.demo.model;

import javax.servlet.http.HttpServletRequest;

public class Utility {
    /**
     * getSiteURL
     *
     * @param request HttpServletRequest
     * @return String
     */
    public static String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}
