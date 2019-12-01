package com.sergeykotov.lift.service;

import com.sergeykotov.lift.domain.Profile;
import com.sergeykotov.lift.domain.State;
import org.springframework.stereotype.Service;

@Service
public class StateService {
    public State create(Profile profile) {
        State state = new State();
        profile.getLifts().forEach(lift -> state.getLiftFloorMap().put(lift, null));
        return state;
    }

    public void update(State state) {
        //TODO: update liftFloorMap according to the defined schedule
    }
}