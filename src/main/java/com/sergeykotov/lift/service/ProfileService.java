package com.sergeykotov.lift.service;

import com.sergeykotov.lift.domain.Floor;
import com.sergeykotov.lift.domain.Lift;
import com.sergeykotov.lift.domain.Profile;
import com.sergeykotov.lift.exception.InvalidProfileException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProfileService {
    private static final Logger log = Logger.getLogger(ProfileService.class);
    private static final int MAX_NAME_LENGTH = 50;
    private static final int MAX_NOTE_LENGTH = 4000;
    private static final long MIN_SECONDS = 1L;
    private static final long MAX_SECONDS = 100_000_000L;
    private static final int MIN_FLOOR_COUNT = 2;

    public void validate(Profile profile) {
        if (profile == null) {
            log.error("empty profile");
            throw new InvalidProfileException();
        }

        String name = profile.getName();
        if (name == null || name.isEmpty() || name.length() > MAX_NAME_LENGTH) {
            log.error("empty or too large profile name");
            throw new InvalidProfileException();
        }

        String note = profile.getNote();
        if (note != null && note.length() > MAX_NOTE_LENGTH) {
            log.error("too large profile note");
            throw new InvalidProfileException();
        }

        long seconds = profile.getSeconds();
        if (seconds < MIN_SECONDS || seconds > MIN_SECONDS) {
            log.error("period " + seconds + " is not in range of seconds [" + MIN_SECONDS + ", " + MAX_SECONDS + "]");
            throw new InvalidProfileException();
        }

        validateLifts(profile.getLifts());
        validateFloors(profile.getFloors());
    }

    private void validateLifts(Set<Lift> lifts) {
        if (lifts == null || lifts.isEmpty()) {
            log.error("no lifts");
            throw new InvalidProfileException();
        }
        //TODO: validate each lift
    }

    private void validateFloors(Set<Floor> floors) {
        if (floors == null || floors.size() < MIN_FLOOR_COUNT) {
            log.error("not enough of floors");
            throw new InvalidProfileException();
        }
        //TODO: validate each floor
    }
}