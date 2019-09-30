package com.sergeykotov.lift.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Session {
    private final long id;
    private final Profile profile;
    private State state;
    private Metrics metrics;
    private LocalDateTime start;
    private LocalDateTime end;

    public Session(long id, Profile profile) {
        this.id = id;
        this.profile = profile;
    }

    public long getId() {
        return id;
    }

    public Profile getProfile() {
        return profile;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Metrics getMetrics() {
        return metrics;
    }

    public void setMetrics(Metrics metrics) {
        this.metrics = metrics;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return getId() == session.getId();
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