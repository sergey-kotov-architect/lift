package com.sergeykotov.lift.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "no session has been found with such ID")
public class NoSessionException extends RuntimeException {
}