package com.sergeykotov.lift.service;

import com.sergeykotov.lift.domain.Metrics;
import com.sergeykotov.lift.domain.Request;
import com.sergeykotov.lift.domain.State;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetricsService {
    public Metrics evaluate(State state) {
        List<Request> requests = state.getRequests();
        Metrics metrics = new Metrics();
        //TODO: implement metrics evaluation
        return metrics;
    }
}