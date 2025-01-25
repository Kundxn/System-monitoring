package com.example.systemmonitor.controller;

import com.example.systemmonitor.service.SystemMonitorService;
import com.example.systemmonitor.model.SystemMetrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SystemController {

    @Autowired
    private SystemMonitorService systemMonitorService;

    @GetMapping("/system-metrics")
    public SystemMetrics getSystemMetrics() {
        // Logic to get and return system metrics
        return systemMonitorService.getSystemMetrics();
    }
}
