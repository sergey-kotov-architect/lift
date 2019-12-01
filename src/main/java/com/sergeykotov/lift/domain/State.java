package com.sergeykotov.lift.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class State {
    private final Map<Lift, Floor> liftFloorMap = new HashMap<>();
    private List<Request> requests;
    private Schedule schedule;

    public State() {
    }

    @JsonProperty("lift_floor_map")
    public Map<Lift, Floor> getLiftFloorMap() {
        return liftFloorMap;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}