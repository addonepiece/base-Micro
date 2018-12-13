package com.dly.cms.hystric;

import com.dly.cms.crossservice.ScheduleAuth;
import org.springframework.stereotype.Component;

@Component
public class ScheduleAuthHystric implements ScheduleAuth {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry " + name;
    }
}
