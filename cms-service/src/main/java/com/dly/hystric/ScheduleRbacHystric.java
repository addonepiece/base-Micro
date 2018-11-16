package com.dly.hystric;

import com.dly.crossservice.ScheduleRbac;
import org.springframework.stereotype.Component;

@Component
public class ScheduleRbacHystric implements ScheduleRbac {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry " + name;
    }
}
