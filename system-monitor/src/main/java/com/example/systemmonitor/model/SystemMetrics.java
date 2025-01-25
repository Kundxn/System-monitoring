package com.example.systemmonitor.model;

import java.util.List;

public class SystemMetrics {
    private List<CpuCore> cpuCores;
    private MemoryInfo memoryInfo;
    private List<DiskInfo> diskInfo;
    private NetworkInfo networkInfo;
    private List<ProcessInfo> processes;

    // Getters and Setters
    public List<CpuCore> getCpuCores() {
        return cpuCores;
    }

    public void setCpuCores(List<CpuCore> cpuCores) {
        this.cpuCores = cpuCores;
    }

    public MemoryInfo getMemoryInfo() {
        return memoryInfo;
    }

    public void setMemoryInfo(MemoryInfo memoryInfo) {
        this.memoryInfo = memoryInfo;
    }

    public List<DiskInfo> getDiskInfo() {
        return diskInfo;
    }

    public void setDiskInfo(List<DiskInfo> diskInfo) {
        this.diskInfo = diskInfo;
    }

    public NetworkInfo getNetworkInfo() {
        return networkInfo;
    }

    public void setNetworkInfo(NetworkInfo networkInfo) {
        this.networkInfo = networkInfo;
    }

    public List<ProcessInfo> getProcesses() {
        return processes;
    }

    public void setProcesses(List<ProcessInfo> processes) {
        this.processes = processes;
    }

    public static class CpuCore {
        private int coreId;
        private double utilization;
        private double clockSpeed;
        private double temperature;

        public int getCoreId() {
            return coreId;
        }

        public void setCoreId(int coreId) {
            this.coreId = coreId;
        }

        public double getUtilization() {
            return utilization;
        }

        public void setUtilization(double utilization) {
            this.utilization = utilization;
        }

        public double getClockSpeed() {
            return clockSpeed;
        }

        public void setClockSpeed(double clockSpeed) {
            this.clockSpeed = clockSpeed;
        }

        public double getTemperature() {
            return temperature;
        }

        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }
    }

    public static class MemoryInfo {
        private long total;
        private long used;
        private long free;
        private long cached;
        private long swapTotal;
        private long swapUsed;
        private long swapFree;

        public long getTotal() {
            return total;
        }

        public void setTotal(long total) {
            this.total = total;
        }

        public long getUsed() {
            return used;
        }

        public void setUsed(long used) {
            this.used = used;
        }

        public long getFree() {
            return free;
        }

        public void setFree(long free) {
            this.free = free;
        }

        public long getCached() {
            return cached;
        }

        public void setCached(long cached) {
            this.cached = cached;
        }

        public long getSwapTotal() {
            return swapTotal;
        }

        public void setSwapTotal(long swapTotal) {
            this.swapTotal = swapTotal;
        }

        public long getSwapUsed() {
            return swapUsed;
        }

        public void setSwapUsed(long swapUsed) {
            this.swapUsed = swapUsed;
        }

        public long getSwapFree() {
            return swapFree;
        }

        public void setSwapFree(long swapFree) {
            this.swapFree = swapFree;
        }
    }

    public static class DiskInfo {
        private String name;
        private long readSpeed;
        private long writeSpeed;
        private long totalSpace;
        private long usedSpace;
        private long freeSpace;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getReadSpeed() {
            return readSpeed;
        }

        public void setReadSpeed(long readSpeed) {
            this.readSpeed = readSpeed;
        }

        public long getWriteSpeed() {
            return writeSpeed;
        }

        public void setWriteSpeed(long writeSpeed) {
            this.writeSpeed = writeSpeed;
        }

        public long getTotalSpace() {
            return totalSpace;
        }

        public void setTotalSpace(long totalSpace) {
            this.totalSpace = totalSpace;
        }

        public long getUsedSpace() {
            return usedSpace;
        }

        public void setUsedSpace(long usedSpace) {
            this.usedSpace = usedSpace;
        }

        public long getFreeSpace() {
            return freeSpace;
        }

        public void setFreeSpace(long freeSpace) {
            this.freeSpace = freeSpace;
        }
    }

    public static class NetworkInfo {
        private long uploadSpeed;
        private long downloadSpeed;
        private long totalBandwidth;
        private int activeConnections;

        public long getUploadSpeed() {
            return uploadSpeed;
        }

        public void setUploadSpeed(long uploadSpeed) {
            this.uploadSpeed = uploadSpeed;
        }

        public long getDownloadSpeed() {
            return downloadSpeed;
        }

        public void setDownloadSpeed(long downloadSpeed) {
            this.downloadSpeed = downloadSpeed;
        }

        public long getTotalBandwidth() {
            return totalBandwidth;
        }

        public void setTotalBandwidth(long totalBandwidth) {
            this.totalBandwidth = totalBandwidth;
        }

        public int getActiveConnections() {
            return activeConnections;
        }

        public void setActiveConnections(int activeConnections) {
            this.activeConnections = activeConnections;
        }
    }

    public static class ProcessInfo {
        private long pid;
        private String name;
        private double cpuUsage;
        private long memoryUsage;
        private long diskRead;
        private long diskWrite;

        public long getPid() {
            return pid;
        }

        public void setPid(long pid) {
            this.pid = pid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getCpuUsage() {
            return cpuUsage;
        }

        public void setCpuUsage(double cpuUsage) {
            this.cpuUsage = cpuUsage;
        }

        public long getMemoryUsage() {
            return memoryUsage;
        }

        public void setMemoryUsage(long memoryUsage) {
            this.memoryUsage = memoryUsage;
        }

        public long getDiskRead() {
            return diskRead;
        }

        public void setDiskRead(long diskRead) {
            this.diskRead = diskRead;
        }

        public long getDiskWrite() {
            return diskWrite;
        }

        public void setDiskWrite(long diskWrite) {
            this.diskWrite = diskWrite;
        }
    }
}