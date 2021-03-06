package com.sergeykotov.lift.task;

import com.sergeykotov.lift.domain.Metrics;
import com.sergeykotov.lift.domain.Profile;
import com.sergeykotov.lift.domain.Session;
import com.sergeykotov.lift.service.MetricsService;
import com.sergeykotov.lift.service.RequestService;
import com.sergeykotov.lift.service.ScheduleService;
import com.sergeykotov.lift.service.StateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class SessionTask extends Thread {
    private static final Logger log = LoggerFactory.getLogger(SessionTask.class);
    private static final long STATE_UPDATE_FREQUENCY = 1000L;

    private final StateService stateService;
    private final RequestService requestService;
    private final ScheduleService scheduleService;
    private final MetricsService metricsService;

    private final Session session;

    public SessionTask(Session session,
                       StateService stateService,
                       RequestService requestService,
                       ScheduleService scheduleService,
                       MetricsService metricsService) {
        this.session = session;
        this.stateService = stateService;
        this.requestService = requestService;
        this.scheduleService = scheduleService;
        this.metricsService = metricsService;
        setName("session " + session);
    }

    @Override
    public void run() {
        log.info("session " + session + " has been initiated");
        session.setStart(LocalDateTime.now());

        Profile profile = session.getProfile();
        com.sergeykotov.lift.domain.State state = stateService.create(profile);
        for (long i = 0; i < profile.getMilliseconds(); i += STATE_UPDATE_FREQUENCY) {
            long start = System.currentTimeMillis();

            stateService.update(state);
            state.setRequests(requestService.generateRequests());
            state.setSchedule(scheduleService.generate(state));

            long elapsed = System.currentTimeMillis() - start;
            if (elapsed > STATE_UPDATE_FREQUENCY) {
                log.error(getName() + ": iteration took " + elapsed + " exceeding " + STATE_UPDATE_FREQUENCY);
                continue;
            }
            LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(STATE_UPDATE_FREQUENCY - elapsed));
        }

        session.setEnd(LocalDateTime.now());
        log.info("session " + session + " has been completed");

        Metrics metrics = metricsService.evaluate(state);
        session.setMetrics(metrics);
        log.info("session " + session + " metrics have been evaluated");
    }
}