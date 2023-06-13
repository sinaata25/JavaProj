package com.amirheshmati.javaproj.models;

import java.util.List;

public class Teacher {
    int code;
    String name;
    String family;
    List<Integer>listLessonsPresentation;
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

    public List<Integer> getListLessonsPresentation() {
        return listLessonsPresentation;
    }

    public void setListLessonsPresentation(List<Integer> listLessonsPresentation) {
        this.listLessonsPresentation = listLessonsPresentation;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    String h;

    public String getH() {
        return h;
    }

    public void setH(String h) {
        this.h = h;
    }
}
