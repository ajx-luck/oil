package com.breast.oil.po;

import org.thymeleaf.util.StringUtils;

public class Location {
    public String country;
    public String province;
    public String city;
    public String region;
    @Override
    public String toString() {
        return "Location{" +
                "country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public String getProvince(){
        if(StringUtils.isEmptyOrWhitespace(province)){
            return region;
        }else{
            return province;
        }
    }
}
