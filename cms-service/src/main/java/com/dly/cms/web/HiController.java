package com.dly.cms.web;

import com.dly.cms.crossservice.ScheduleAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {
    @Autowired
    ScheduleAuth scheduleAuth;

    @GetMapping(value = "/hi")
    public String sayHi(@RequestParam String name) {
        return scheduleAuth.sayHiFromClientOne( name );
    }

}
