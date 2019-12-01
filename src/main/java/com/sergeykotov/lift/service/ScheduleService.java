package com.sergeykotov.lift.service;

import com.sergeykotov.lift.domain.Schedule;
import com.sergeykotov.lift.domain.State;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
    public Schedule generate(State state) {
        Schedule schedule = new Schedule();
        //TODO: implement schedule generation
        return schedule;
    }
}