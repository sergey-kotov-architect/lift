package com.sergeykotov.lift.service;

import com.sergeykotov.lift.domain.Profile;
import com.sergeykotov.lift.domain.Session;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {
    private static final Logger log = Logger.getLogger(SessionService.class);

    public long create(Profile profile) {
        log.info("creating a session based on profile " + profile);
        throw new RuntimeException("operation not supported yet");
    }

    public List<Session> getAll() {
        throw new RuntimeException("operation not supported yet");
    }

    public Session get(long id) {
        throw new RuntimeException("operation not supported yet");
    }
}