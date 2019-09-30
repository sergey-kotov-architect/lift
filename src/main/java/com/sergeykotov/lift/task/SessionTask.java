package com.sergeykotov.lift.task;

import com.sergeykotov.lift.domain.Metrics;
import com.sergeykotov.lift.domain.Session;
import com.sergeykotov.lift.service.MetricsService;
import com.sergeykotov.lift.service.ScheduleService;
import com.sergeykotov.lift.service.StateService;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;

public class SessionTask extends Thread {
    private static final Logger log = Logger.getLogger(SessionTask.class);

    private final StateService stateService = new StateService();
    private final ScheduleService scheduleService = new ScheduleService();
    private final MetricsService metricsService = new MetricsService();

    private final Session session;

    public SessionTask(Session session) {
        this.session = session;
        setName("session " + session);
    }

    @Override
    public void run() {
        log.info("session " + session + " has been initiated");
        session.setStart(LocalDateTime.now());

        for (int i = 0; i < session.getProfile().getSeconds(); i++) {
            stateService.update(session.getState());
            scheduleService.generate(session.getState());
        }

        session.setEnd(LocalDateTime.now());
        log.info("session " + session + " has been completed");

        Metrics metrics = metricsService.evaluate(session.getState());
        session.setMetrics(metrics);
        log.info("session " + session + " metrics have been evaluated");
    }
}