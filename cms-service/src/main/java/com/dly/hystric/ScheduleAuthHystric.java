package com.dly.hystric;

import com.dly.crossservice.ScheduleAuth;
import org.springframework.stereotype.Component;

@Component
public class ScheduleAuthHystric implements ScheduleAuth {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry " + name;
    }
}
