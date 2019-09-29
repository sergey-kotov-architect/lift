package com.sergeykotov.lift.controller;

import com.sergeykotov.lift.domain.Profile;
import com.sergeykotov.lift.domain.Session;
import com.sergeykotov.lift.service.SecurityService;
import com.sergeykotov.lift.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class Controller {
    private final SessionService sessionService;
    private final SecurityService securityService;

    @Autowired
    public Controller(SessionService sessionService, SecurityService securityService) {
        this.sessionService = sessionService;
        this.securityService = securityService;
    }

    @PostMapping("session")
    public Session createSession(@RequestHeader String authorization, @RequestBody Profile profile) {
        securityService.verifyRequest(authorization);
        return sessionService.create(profile);
    }

    @GetMapping("session")
    public List<Session> getSessions(@RequestHeader String authorization) {
        securityService.verifyRequest(authorization);
        return sessionService.getAll();
    }

    @GetMapping("session/{id}")
    public Session getSession(@PathVariable long id, @RequestHeader String authorization) {
        securityService.verifyRequest(authorization);
        return sessionService.get(id);
    }

    @DeleteMapping("session")
    public void deleteSessions(@RequestHeader String authorization) {
        securityService.verifyRequest(authorization);
        sessionService.deleteAll();
    }

    @DeleteMapping("session/{id}")
    public void deleteSession(@PathVariable long id, @RequestHeader String authorization) {
        securityService.verifyRequest(authorization);
        sessionService.delete(id);
    }
}