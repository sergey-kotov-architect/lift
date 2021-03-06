package com.sergeykotov.lift.service;

import com.sergeykotov.lift.domain.Profile;
import com.sergeykotov.lift.domain.Session;
import com.sergeykotov.lift.exception.NoSessionException;
import com.sergeykotov.lift.exception.SessionPoolException;
import com.sergeykotov.lift.task.SessionTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class SessionService {
    private static final Logger log = LoggerFactory.getLogger(SessionService.class);

    private static final int POOL_SIZE = Integer.MAX_VALUE;
    private static final List<Session> sessions = new CopyOnWriteArrayList<>();
    private static final AtomicLong sessionCounter = new AtomicLong(0L);

    private static final int PROCESSOR_COUNT = Runtime.getRuntime().availableProcessors();
    private static final ExecutorService executorService = Executors.newFixedThreadPool(PROCESSOR_COUNT);

    private final StateService stateService;
    private final RequestService requestService;
    private final ScheduleService scheduleService;
    private final MetricsService metricsService;

    @Autowired
    public SessionService(StateService stateService,
                          RequestService requestService,
                          ScheduleService scheduleService,
                          MetricsService metricsService) {
        this.stateService = stateService;
        this.requestService = requestService;
        this.scheduleService = scheduleService;
        this.metricsService = metricsService;
    }

    public Session create(Profile profile) {
        log.info("creating a new session based on profile " + profile + "...");
        if (sessions.size() == POOL_SIZE) {
            log.error("session pool is full: " + POOL_SIZE);
            throw new SessionPoolException();
        }
        Session session = new Session(sessionCounter.incrementAndGet(), profile);
        sessions.add(session);
        log.info("session " + session + " has been created based on profile " + profile);

        SessionTask sessionTask = new SessionTask(session, stateService, requestService, scheduleService, metricsService);
        executorService.submit(sessionTask);
        return session;
    }

    public List<Session> getAll() {
        return sessions;
    }

    public Session getById(long id) {
        return sessions.stream().filter(s -> s.getId() == id).findAny().orElseThrow(NoSessionException::new);
    }

    public void deleteAll() {
        log.info("deleting all " + sessions.size() + " sessions...");
        sessions.clear();
        log.info("all sessions have been deleted");
    }

    public void deleteById(long id) {
        log.info("deleting session by ID " + id + "...");
        Optional<Session> session = sessions.stream().filter(s -> s.getId() == id).findAny();
        if (!session.isPresent()) {
            log.error("no session with ID " + id + " to delete");
            throw new NoSessionException();
        }
        sessions.remove(session.get());
        log.info("session " + session.get() + " has been deleted");
    }
}