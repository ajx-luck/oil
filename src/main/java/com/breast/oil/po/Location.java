package com.breast.oil.po;

public class Location {
    public String country;
    public String province;
    public String city;

    @Override
    public String toString() {
        return "Location{" +
                "country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
