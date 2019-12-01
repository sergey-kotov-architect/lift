package com.sergeykotov.lift.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Objects;

public class Request {
    private final long id;
    private final Floor currentFloor;
    private final Floor floor;
    private final Lift lift;
    private final LocalDateTime dateTime = LocalDateTime.now();
    private LocalDateTime processed;

    public Request(long id, Floor currentFloor, Floor floor, Lift lift) {
        this.id = id;
        this.currentFloor = currentFloor;
        this.floor = floor;
        this.lift = lift;
    }

    public long getId() {
        return id;
    }

    @JsonProperty("current_floor")
    public Floor getCurrentFloor() {
        return currentFloor;
    }

    public Floor getFloor() {
        return floor;
    }

    public Lift getLift() {
        return lift;
    }

    @JsonProperty("date_time")
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public LocalDateTime getProcessed() {
        return processed;
    }

    public void setProcessed(LocalDateTime processed) {
        this.processed = processed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return getId() == request.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return String.valueOf(getId());
    }
}