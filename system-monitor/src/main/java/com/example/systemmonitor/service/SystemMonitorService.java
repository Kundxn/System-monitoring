package com.example.systemmonitor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.systemmonitor.model.SystemMetrics;
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.software.os.OperatingSystem;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SystemMonitorService {
    private static final Logger logger = LoggerFactory.getLogger(SystemMonitorService.class);
    private final SystemInfo systemInfo;
    private final HardwareAbstractionLayer hardware;
    private final OperatingSystem os;
    private Map<String, Long> lastNetworkBytes;
    private Map<String, Long[]> lastDiskBytes;

    public SystemMonitorService() {
        this.systemInfo = new SystemInfo();
        this.hardware = systemInfo.getHardware();
        this.os = systemInfo.getOperatingSystem();
        this.lastNetworkBytes = new HashMap<>();
        this.lastDiskBytes = new HashMap<>();
    }

    public SystemMetrics getSystemMetrics() {
        SystemMetrics metrics = new SystemMetrics();
        metrics.setCpuCores(getCpuMetrics());
        metrics.setMemoryInfo(getMemoryMetrics());
        metrics.setDiskInfo(getDiskMetrics());
        metrics.setNetworkInfo(getNetworkMetrics());
        return metrics;
    }

    private List<SystemMetrics.CpuCore> getCpuMetrics() {
        CentralProcessor processor = hardware.getProcessor();
        long[][] prevTicks = processor.getProcessorCpuLoadTicks();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        long[][] currentTicks = processor.getProcessorCpuLoadTicks();

        return Arrays.stream(processor.getProcessorCpuLoadBetweenTicks(prevTicks))
                .mapToObj(load -> {
                    SystemMetrics.CpuCore core = new SystemMetrics.CpuCore();
                    core.setUtilization(load * 100); // Convert to percentage
                    core.setClockSpeed(processor.getMaxFreq() / 1_000_000_000.0); // GHz
                    core.setTemperature(hardware.getSensors().getCpuTemperature()); // °C
                    return core;
                })
                .collect(Collectors.toList());
    }

    private SystemMetrics.MemoryInfo getMemoryMetrics() {
        GlobalMemory memory = hardware.getMemory();
        VirtualMemory swap = memory.getVirtualMemory();

        SystemMetrics.MemoryInfo memInfo = new SystemMetrics.MemoryInfo();
        memInfo.setTotal(memory.getTotal());
        memInfo.setUsed(memory.getTotal() - memory.getAvailable());
        memInfo.setFree(memory.getAvailable());
        memInfo.setCached(memory.getPageSize());
        memInfo.setSwapTotal(swap.getSwapTotal());
        memInfo.setSwapUsed(swap.getSwapUsed());
        memInfo.setSwapFree(swap.getSwapTotal() - swap.getSwapUsed());

        return memInfo;
    }

    private List<SystemMetrics.DiskInfo> getDiskMetrics() {
        List<SystemMetrics.DiskInfo> diskInfoList = hardware.getDiskStores().stream()
                .flatMap(disk -> os.getFileSystem().getFileStores().stream()
                        .filter(fs -> fs.getMount().equals(disk.getName()))
                        .map(partition -> {
                            SystemMetrics.DiskInfo diskInfo = new SystemMetrics.DiskInfo();
                            diskInfo.setName(partition.getName());

                            Long[] lastBytes = lastDiskBytes.getOrDefault(partition.getName(), new Long[]{0L, 0L});
                            long readSpeed = disk.getReadBytes() - lastBytes[0];
                            long writeSpeed = disk.getWriteBytes() - lastBytes[1];

                            diskInfo.setReadSpeed(readSpeed);
                            diskInfo.setWriteSpeed(writeSpeed);
                            diskInfo.setTotalSpace(partition.getTotalSpace());
                            diskInfo.setUsedSpace(partition.getTotalSpace() - partition.getUsableSpace());
                            diskInfo.setFreeSpace(partition.getUsableSpace());

                            lastDiskBytes.put(partition.getName(), new Long[]{disk.getReadBytes(), disk.getWriteBytes()});

                            logger.info("Disk: {} | Read Speed: {} | Write Speed: {} | Total Space: {} | Used Space: {} | Free Space: {}",
                                    partition.getName(), readSpeed, writeSpeed, partition.getTotalSpace(),
                                    diskInfo.getUsedSpace(), diskInfo.getFreeSpace());

                            return diskInfo;
                        }))
                .collect(Collectors.toList());

        return diskInfoList;
    }

    private SystemMetrics.NetworkInfo getNetworkMetrics() {
        SystemMetrics.NetworkInfo netInfo = new SystemMetrics.NetworkInfo();

        hardware.getNetworkIFs().forEach(net -> {
            String name = net.getName();
            long currentBytes = net.getBytesRecv() + net.getBytesSent();
            long lastBytes = lastNetworkBytes.getOrDefault(name, 0L);

            netInfo.setUploadSpeed(net.getBytesSent() - (lastBytes / 2));
            netInfo.setDownloadSpeed(net.getBytesRecv() - (lastBytes / 2));
            netInfo.setTotalBandwidth(currentBytes);

            lastNetworkBytes.put(name, currentBytes);

            logger.info("Network Interface: {} | Upload Speed: {} | Download Speed: {} | Total Bandwidth: {}",
                    name, netInfo.getUploadSpeed(), netInfo.getDownloadSpeed(), netInfo.getTotalBandwidth());
        });

        return netInfo;
    }
}
