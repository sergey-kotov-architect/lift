package com.sergeykotov.lift.controller;

import com.sergeykotov.lift.domain.Profile;
import com.sergeykotov.lift.domain.Session;
import com.sergeykotov.lift.service.AuthorizationService;
import com.sergeykotov.lift.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class Controller {
    private final AuthorizationService authorizationService;
    private final SessionService sessionService;

    @Autowired
    public Controller(AuthorizationService authorizationService, SessionService sessionService) {
        this.authorizationService = authorizationService;
        this.sessionService = sessionService;
    }

    @PostMapping("session")
    public Session createSession(@RequestHeader String authorization, @RequestBody @Valid Profile profile) {
        authorizationService.authorize(authorization);
        return sessionService.create(profile);
    }

    @GetMapping("session")
    public List<Session> getSessions(@RequestHeader String authorization) {
        authorizationService.authorize(authorization);
        return sessionService.getAll();
    }

    @GetMapping("session/{id}")
    public Session getSession(@RequestHeader String authorization, @PathVariable long id) {
        authorizationService.authorize(authorization);
        return sessionService.getById(id);
    }

    @DeleteMapping("session")
    public void deleteSessions(@RequestHeader String authorization) {
        authorizationService.authorize(authorization);
        sessionService.deleteAll();
    }

    @DeleteMapping("session/{id}")
    public void deleteSession(@RequestHeader String authorization, @PathVariable long id) {
        authorizationService.authorize(authorization);
        sessionService.deleteById(id);
    }
}