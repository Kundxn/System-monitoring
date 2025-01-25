package com.example.systemmonitor.scheduler;

import com.example.systemmonitor.service.SystemMonitorService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MetricsScheduler {
    private final SystemMonitorService monitorService;
    private final SimpMessagingTemplate messagingTemplate;

    public MetricsScheduler(SystemMonitorService monitorService, SimpMessagingTemplate messagingTemplate) {
        this.monitorService = monitorService;
        this.messagingTemplate = messagingTemplate;
    }

    @Scheduled(fixedRate = 1000)
    public void sendMetrics() {
        messagingTemplate.convertAndSend("/topic/metrics", monitorService.getSystemMetrics());
    }
}