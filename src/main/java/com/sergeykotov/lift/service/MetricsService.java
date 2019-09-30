package com.sergeykotov.lift.service;

import com.sergeykotov.lift.domain.Metrics;
import com.sergeykotov.lift.domain.State;
import org.springframework.stereotype.Service;

@Service
public class MetricsService {
    public Metrics evaluate(State state) {
        Metrics metrics = new Metrics();
        //TODO: implement metrics evaluation
        return metrics;
    }
}