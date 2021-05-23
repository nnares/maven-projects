package com.nish.camel.bean;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TimeBean {

    public String getCurrentTime(){
        return "time now - " + LocalDateTime.now();
    }

}
