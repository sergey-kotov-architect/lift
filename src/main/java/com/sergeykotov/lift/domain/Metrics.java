package com.sergeykotov.lift.domain;

public class Metrics {
    private long requests;
    private long processedRequests;

    private long minRequestTime;
    private long maxRequestTime;
    private double meanRequestTime;

    private double meanDistance;
    private String note;

    public Metrics() {
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}