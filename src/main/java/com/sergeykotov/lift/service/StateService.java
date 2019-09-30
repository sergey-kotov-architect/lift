package com.sergeykotov.lift.service;

import com.sergeykotov.lift.domain.Floor;
import com.sergeykotov.lift.domain.Lift;
import com.sergeykotov.lift.domain.Profile;
import com.sergeykotov.lift.domain.State;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StateService {
    public State create(Profile profile) {
        State state = new State();
        profile.getLifts().forEach(lift -> state.getLiftFloorMap().put(lift, null));
        return state;
    }

    public void update(State state) {
        Map<Lift, Floor> liftFloorMap = state.getLiftFloorMap();
        for (Map.Entry<Lift, Floor> entry : liftFloorMap.entrySet()) {
            Lift lift = entry.getKey();
            Floor floor = entry.getValue();
            Floor current = lift.getFloor();
            if (floor == null || current.equals(floor)) {
                continue;
            }
            Floor next = null;
            lift.setFloor(next);
        }
        //TODO: update requests and check if new requests were sent
    }
}