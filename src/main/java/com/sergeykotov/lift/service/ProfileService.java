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

    private static final int MAX_LIFT_COUNT = Integer.MAX_VALUE;
    private static final int MAX_LIFT_NAME_LENGTH = 50;
    private static final int MAX_LIFT_NOTE_LENGTH = 4000;
    private static final int MIN_LIFT_CAPACITY = 1;
    private static final int MAX_LIFT_CAPACITY = Integer.MAX_VALUE;
    private static final int MIN_LIFT_SPEED = 1;
    private static final int MAX_LIFT_SPEED = Integer.MAX_VALUE;

    private static final int MAX_FLOOR_COUNT = Integer.MAX_VALUE;
    private static final int MAX_FLOOR_NAME_LENGTH = 50;
    private static final int MAX_FLOOR_NOTE_LENGTH = 4000;
    private static final int MIN_FLOOR_POPULATION = 0;
    private static final int MAX_FLOOR_POPULATION = Integer.MAX_VALUE;

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
        if (seconds < MIN_SECONDS || seconds > MAX_SECONDS) {
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

        if (lifts.size() > MAX_LIFT_COUNT) {
            log.error("lift count " + lifts.size() + " exceeds " + MAX_LIFT_COUNT);
            throw new InvalidProfileException();
        }

        for (Lift lift : lifts) {
            String name = lift.getName();
            if (name == null || name.isEmpty() || name.length() > MAX_LIFT_NAME_LENGTH) {
                log.error("lift " + lift + " has an empty or too large name");
                throw new InvalidProfileException();
            }

            String note = lift.getNote();
            if (note != null && note.length() > MAX_LIFT_NOTE_LENGTH) {
                log.error("lift " + lift + " has a too large note");
                throw new InvalidProfileException();
            }

            int capacity = lift.getCapacity();
            if (capacity < MIN_LIFT_CAPACITY || capacity > MAX_LIFT_CAPACITY) {
                log.error("lift " + lift + " has an invalid capacity " + capacity);
                throw new InvalidProfileException();
            }

            int speed = lift.getSpeed();
            if (speed < MIN_LIFT_SPEED || speed > MAX_LIFT_SPEED) {
                log.error("lift " + lift + " has an invalid speed " + speed);
                throw new InvalidProfileException();
            }
        }
    }

    private void validateFloors(Set<Floor> floors) {
        if (floors == null || floors.size() < 2) {
            log.error("not enough floors");
            throw new InvalidProfileException();
        }

        if (floors.size() > MAX_FLOOR_COUNT) {
            log.error("floor count " + floors.size() + " exceeds " + MAX_FLOOR_COUNT);
            throw new InvalidProfileException();
        }

        for (Floor floor : floors) {
            String name = floor.getName();
            if (name == null || name.isEmpty() || name.length() > MAX_FLOOR_NAME_LENGTH) {
                log.error("floor " + floor + " has an empty or too large name");
                throw new InvalidProfileException();
            }

            String note = floor.getNote();
            if (note != null && note.length() > MAX_FLOOR_NOTE_LENGTH) {
                log.error("floor " + floor + " has a too large note");
                throw new InvalidProfileException();
            }

            int population = floor.getPopulation();
            if (population < MIN_FLOOR_POPULATION || population > MAX_FLOOR_POPULATION) {
                log.error("floor " + floor + " has an invalid population " + population);
                throw new InvalidProfileException();
            }
        }
    }
}