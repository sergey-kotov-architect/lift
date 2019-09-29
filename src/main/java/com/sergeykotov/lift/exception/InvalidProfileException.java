package com.sergeykotov.lift.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "invalid profile")
public class InvalidProfileException extends RuntimeException {
}