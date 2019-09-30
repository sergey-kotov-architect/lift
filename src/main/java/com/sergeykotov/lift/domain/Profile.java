package com.sergeykotov.lift.domain;

import java.util.List;
import java.util.Objects;

public class Profile {
    private long id;
    private String name;
    private String note;
    private List<Lift> lifts;
    private List<Floor> floors;
    private long seconds;

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

    public List<Lift> getLifts() {
        return lifts;
    }

    public void setLifts(List<Lift> lifts) {
        this.lifts = lifts;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

    public long getSeconds() {
        return seconds;
    }

    public void setSeconds(long seconds) {
        this.seconds = seconds;
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