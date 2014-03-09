package fr.cma.aurora.model;

import org.joda.time.DateTime;

public class Measure {
    private String satellite;
    private String hemisphere;
    private DateTime date;
    private float power;
    private int activityLevel;
    private float normalizingFactor;

    public String getSatellite() {
        return satellite;
    }

    public void setSatellite(String satellite) {
        this.satellite = satellite;
    }

    public String getHemisphere() {
        return hemisphere;
    }

    public void setHemisphere(String hemisphere) {
        this.hemisphere = hemisphere;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public int getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(int activityLevel) {
        this.activityLevel = activityLevel;
    }

    public float getNormalizingFactor() {
        return normalizingFactor;
    }

    public void setNormalizingFactor(float normalizingFactor) {
        this.normalizingFactor = normalizingFactor;
    }
}
