package com.sergeykotov.lift.service;

import com.sergeykotov.lift.exception.AuthorizationException;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
    private static final String AUTHORIZATION = "Basic YWRtaW46cm9vdA==";

    public void verifyRequest(String authorization) {
        if (!AUTHORIZATION.equals(authorization)) {
            throw new AuthorizationException();
        }
    }
}