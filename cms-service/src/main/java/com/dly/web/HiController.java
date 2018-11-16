package com.dly.web;

import com.dly.crossservice.ScheduleRbac;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {
    @Autowired
    ScheduleRbac scheduleRbac;

    @GetMapping(value = "/hi")
    public String sayHi(@RequestParam String name) {
        return scheduleRbac.sayHiFromClientOne( name );
    }

}
