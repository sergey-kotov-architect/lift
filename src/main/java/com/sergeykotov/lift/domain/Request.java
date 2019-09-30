package com.sergeykotov.lift.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Request {
    private final long id;
    private final Session session;
    private final Floor currentFloor;
    private final Floor floor;
    private final Lift lift;
    private final LocalDateTime dateTime;
    private LocalDateTime processed;

    public Request(long id, Session session, Floor currentFloor, Floor floor, Lift lift) {
        this.id = id;
        this.session = session;
        this.currentFloor = currentFloor;
        this.floor = floor;
        this.lift = lift;
        dateTime = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public Session getSession() {
        return session;
    }

    public Floor getCurrentFloor() {
        return currentFloor;
    }

    public Floor getFloor() {
        return floor;
    }

    public Lift getLift() {
        return lift;
    }

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