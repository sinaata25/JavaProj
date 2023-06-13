package com.amirheshmati.javaproj.models;

public class Lesson {
    int code;
    String name;
    int unit;
    String PresentationTime;
    String PresentationDay;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public String getPresentationTime() {
        return PresentationTime;
    }

    public void setPresentationTime(String presentationTime) {
        PresentationTime = presentationTime;
    }

    public String getPresentationDay() {
        return PresentationDay;
    }

    public void setPresentationDay(String presentationDay) {
        PresentationDay = presentationDay;
    }
}
