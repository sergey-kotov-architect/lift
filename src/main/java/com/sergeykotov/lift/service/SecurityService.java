package com.sergeykotov.lift.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.sergeykotov.lift.exception.AuthorizationException;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
    private static final String KEY = "$2a$12$6jOq9MGdxKJdXZ3IDTkM1urVg2rovxBfzMOfOAm0PzM4tSelr70wC";

    public void verifyRequest(String authorization) {
        System.out.println(BCrypt.withDefaults().hashToString(12, authorization.toCharArray()));
        if (!BCrypt.verifyer().verify(authorization.toCharArray(), KEY).verified) {
            throw new AuthorizationException();
        }
    }
}