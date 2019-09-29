package com.sergeykotov.lift.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "authorization failed")
public class AuthorizationException extends RuntimeException {
}
