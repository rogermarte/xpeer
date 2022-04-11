package com.example.xpeer.course.domain;

public class Localization {
    private String country;
    private String city;

    public Localization(String country, String city) {
        this.country = country;
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }
}
