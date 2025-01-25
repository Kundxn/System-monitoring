package com.example.systemmonitor.controller;

import com.example.systemmonitor.model.SystemMetrics;
import com.example.systemmonitor.service.SystemMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SystemMetricsController {

    // This method can be removed or commented out as it duplicates the one in SystemController
    /*
    @GetMapping("/system-metrics")
    public SystemMetrics getSystemMetrics() {
        return systemMonitorService.getSystemMetrics();
    }
    */
}

