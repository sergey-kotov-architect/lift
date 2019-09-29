package com.sergeykotov.lift.service;

import com.sergeykotov.lift.Task.RunSession;
import com.sergeykotov.lift.domain.Profile;
import com.sergeykotov.lift.domain.Session;
import com.sergeykotov.lift.exception.NoSessionException;
import com.sergeykotov.lift.exception.SessionPoolException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

//TODO: make POOL_SIZE configurable
//TODO: handle sessionCounter overflow

@Service
public class SessionService {
    private static final int POOL_SIZE = Integer.MAX_VALUE;
    private static final int PROCESSOR_COUNT = Runtime.getRuntime().availableProcessors();

    private static final Logger log = Logger.getLogger(SessionService.class);
    private static final AtomicLong sessionCounter = new AtomicLong(0L);
    private static final List<Session> sessions = new CopyOnWriteArrayList<>();
    private static final ExecutorService executorService = Executors.newFixedThreadPool(PROCESSOR_COUNT);
    private final ProfileService profileService;

    @Autowired
    public SessionService(ProfileService profileService) {
        this.profileService = profileService;
    }

    public long create(Profile profile) {
        log.info("trying to create a new session");
        if (sessions.size() == POOL_SIZE) {
            log.error("session pool is full");
            throw new SessionPoolException();
        }
        profileService.validate(profile);
        long id = sessionCounter.incrementAndGet();
        Session session = new Session(id, profile);
        sessions.add(session);
        RunSession runSession = new RunSession(session);
        executorService.submit(runSession);
        String message = "session %s has been created based on profile %s, session pool size is %d";
        log.info(String.format(message, session, profile, sessions.size()));
        return id;
    }

    public List<Session> getAll() {
        return sessions;
    }

    public Session get(long id) {
        return sessions.stream().filter(s -> s.getId() == id).findAny().orElseThrow(NoSessionException::new);
    }

    public void deleteAll() {
        log.info("deleting all sessions...");
        sessions.clear();
        log.info("all sessions have been deleted");
    }

    public void delete(long id) {
        log.info("deleting session " + id + "...");
        Optional<Session> session = sessions.stream().filter(s -> s.getId() == id).findAny();
        if (!session.isPresent()) {
            log.error("no session with id " + id + " to delete");
            throw new NoSessionException();
        }
        sessions.remove(session.get());
        log.info("session " + session + " has been deleted");
    }
}