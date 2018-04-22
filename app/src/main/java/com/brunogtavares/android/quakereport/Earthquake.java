package com.brunogtavares.android.quakereport;

/**
 * Created by brunogtavares on 4/22/18.
 */

public class Earthquake {

    private Double magnitude;
    private String location;
    private String date;

    public Earthquake(Double magnitude, String location, String date) {
        this.magnitude = magnitude;
        this.location = location;
        this.date = date;
    }


    public Double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Double magnitude) {
        this.magnitude = magnitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
