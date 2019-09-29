package com.sergeykotov.lift.domain;

import java.util.Objects;

public class Session {
    private final long id;
    private final Profile profile;

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