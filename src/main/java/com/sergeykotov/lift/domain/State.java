package com.sergeykotov.lift.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class State {
    private final List<Request> requests = new ArrayList<>();
    private final Map<Lift, Floor> liftFloorMap = new HashMap<>();

    public State() {
    }

    public List<Request> getRequests() {
        return requests;
    }

    public Map<Lift, Floor> getLiftFloorMap() {
        return liftFloorMap;
    }
}