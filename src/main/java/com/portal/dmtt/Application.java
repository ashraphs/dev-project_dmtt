package com.portal.dmtt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@ComponentScan({"com.portal.dmtt.match.Test","com.portal.dmtt.repo"})
//@EnableAutoConfiguration(exclude =exclude { DataSourceAutoConfiguration.class,WebMvcAutoConfiguration.class })
public class Application {

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }



}
