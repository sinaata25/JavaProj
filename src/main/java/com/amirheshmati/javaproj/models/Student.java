package com.amirheshmati.javaproj.models;

import java.util.List;

public class Student {
    int code;
    String name;
    String family;
    List<Integer>listLessons;
    String gender;

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

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public List<Integer> getListLessons() {
        return listLessons;
    }

    public void setListLessons(List<Integer> listLessons) {
        this.listLessons = listLessons;
    }

    public String getGender() {
        return gender;
    }

    String H;

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getH() {
        return H;
    }

    public void setH(String h) {
        H = h;
    }
}
