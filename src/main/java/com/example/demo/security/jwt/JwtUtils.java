package com.example.demo.security.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

import com.example.demo.messages.MessageHandle;
import com.example.demo.security.services.UserDetailsImpl;

/**
 * JwtUtils
 * Version 1.0
 * Date: 07-06-2021
 * Copyright
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 07-06-2021           DUYHV9            Create
 */
@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${duyhuynh.app.jwtSecret}")
    private String jwtSecret;

    @Value("${duyhuynh.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    /**
     * generateJwtToken
     *
     * @param authentication Authentication
     * @return String
     */
    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        // generate jwt
        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    /**
     * getUserNameFromJwtToken
     *
     * @param token String
     * @return String
     */
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * validateJwtToken
     *
     * @param authToken String
     * @return boolean
     */
    public boolean validateJwtToken(String authToken) {
        // check valid token
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error(MessageHandle.JWT_ERROR_1 + "{}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error(MessageHandle.JWT_ERROR_2 + "{}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error(MessageHandle.JWT_ERROR_3 + "{}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error(MessageHandle.JWT_ERROR_4 + "{}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error(MessageHandle.JWT_ERROR_5 + "{}", e.getMessage());
        }

        return false;
    }
}

