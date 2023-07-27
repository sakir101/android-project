package com.example.firebaseconnection;

public class collectData {
    String name, age, duration;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public collectData(String name, String age, String duration) {
        this.name = name;
        this.age = age;
        this.duration = duration;

    }
}
