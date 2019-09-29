package com.sergeykotov.lift.domain;

import java.util.Objects;
import java.util.Set;

public class Profile {
    private long id;
    private String name;
    private String note;
    private Set<Lift> lifts;
    private Set<Floor> floors;

    public Profile() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Set<Lift> getLifts() {
        return lifts;
    }

    public void setLifts(Set<Lift> lifts) {
        this.lifts = lifts;
    }

    public Set<Floor> getFloors() {
        return floors;
    }

    public void setFloors(Set<Floor> floors) {
        this.floors = floors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return getId() == profile.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return getId() + ": " + getName();
    }
}