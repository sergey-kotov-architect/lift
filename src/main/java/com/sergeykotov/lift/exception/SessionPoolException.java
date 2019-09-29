package com.sergeykotov.lift.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED, reason = "session pool is full")
public class SessionPoolException extends RuntimeException {
}