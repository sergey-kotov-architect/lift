package com.sergeykotov.lift.Task;

import com.sergeykotov.lift.domain.Session;
import com.sergeykotov.lift.service.SessionService;
import org.apache.log4j.Logger;

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
        //TODO: implement session running according to its profile
    }
}