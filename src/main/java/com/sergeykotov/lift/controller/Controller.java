package com.sergeykotov.lift.controller;

import com.sergeykotov.lift.domain.Profile;
import com.sergeykotov.lift.domain.Session;
import com.sergeykotov.lift.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO: add configurable API version, authentication layer and too many requests protection

@RestController
@RequestMapping("/api/")
public class Controller {
    private final SessionService sessionService;

    @Autowired
    public Controller(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("session")
    public long createSession(@RequestBody Profile profile) {
        return sessionService.create(profile);
    }

    @GetMapping("session")
    public List<Session> getSessions() {
        return sessionService.getAll();
    }

    @GetMapping("session/{id}")
    public Session getSession(@PathVariable long id) {
        return sessionService.get(id);
    }

    @DeleteMapping("session")
    public void deleteSessions() {
        sessionService.deleteAll();
    }

    @DeleteMapping("session/{id}")
    public void deleteSession(@PathVariable long id) {
        sessionService.delete(id);
    }
}