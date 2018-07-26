package com.breast.oil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by B04e on 2017/11/27.
 */
@SpringBootApplication
@EnableCaching
@EnableScheduling
public class OilInitApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(OilInitApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(OilInitApplication.class, args);
    }
}
