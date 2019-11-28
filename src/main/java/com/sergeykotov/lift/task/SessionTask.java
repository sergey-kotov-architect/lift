package com.sergeykotov.lift.task;

import com.sergeykotov.lift.domain.Metrics;
import com.sergeykotov.lift.domain.Profile;
import com.sergeykotov.lift.domain.Session;
import com.sergeykotov.lift.service.MetricsService;
import com.sergeykotov.lift.service.ScheduleService;
import com.sergeykotov.lift.service.StateService;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;

public class SessionTask extends Thread {
    private static final Logger log = Logger.getLogger(SessionTask.class);
    private static final long STATE_UPDATE_FREQUENCY = 1000L;

    private final StateService stateService;
    private final ScheduleService scheduleService;
    private final MetricsService metricsService;

    private final Session session;

    public SessionTask(Session session,
                       StateService stateService,
                       ScheduleService scheduleService,
                       MetricsService metricsService) {
        this.session = session;
        this.stateService = stateService;
        this.scheduleService = scheduleService;
        this.metricsService = metricsService;
        setName("session " + session.getId());
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
            scheduleService.generate(state);

            long elapsed = System.currentTimeMillis() - start;
            if (elapsed > STATE_UPDATE_FREQUENCY) {
                log.error(getName() + ": iteration took " + elapsed + " exceeding " + STATE_UPDATE_FREQUENCY);
                continue;
            }
            try {
                Thread.sleep(STATE_UPDATE_FREQUENCY - elapsed);
            } catch (InterruptedException e) {
                log.error(getName() + ": interrupted", e);
                return;
            }
        }

        session.setEnd(LocalDateTime.now());
        log.info("session " + session + " has been completed");

        Metrics metrics = metricsService.evaluate(state);
        session.setMetrics(metrics);
        log.info("session " + session + " metrics have been evaluated");
    }
}