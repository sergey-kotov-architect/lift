package com.sergeykotov.lift.Task;

import com.sergeykotov.lift.domain.Metrics;
import com.sergeykotov.lift.domain.Session;
import com.sergeykotov.lift.service.SessionService;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;

public class RunSession extends Thread {
    private static final Logger log = Logger.getLogger(SessionService.class);

    private final Session session;

    public RunSession(Session session) {
        this.session = session;
        setName("session " + session);
    }

    @Override
    public void run() {
        log.info("session " + session + " has been initiated");
        session.setStart(LocalDateTime.now());

        Metrics metrics = session.getMetrics();
        try {
            Thread.sleep(session.getProfile().getSeconds() * 1000); //for initial testing
        } catch (InterruptedException e) {
            log.error(e);
        }
        //TODO: generate a request stream for the given period
        //TODO: process the stream with Scheduler
        //TODO: estimate metrics
        metrics.setNote("done");

        session.setEnd(LocalDateTime.now());
        log.info("session " + session + " has been completed");
    }
}