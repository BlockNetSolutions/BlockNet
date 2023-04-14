package de.blocknet.api.base;

import java.nio.file.Files;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface ICloudBase {

    UUID getUniqueId();

    boolean isConnected();

    String getName();

    String getHostname();

    String getVersion();

    //List<ICloudService> getRunningServices();

    int getMaxServiceCount();

    void setMaxServiceCount(int maxServiceCount);

    int getMinServiceCount();

    void setMinServiceCount(int minServiceCount);

    double getCpuLoad();

    double getMemoryUsage();

    double getFreeMemory();

    double getMaxMemory();

    void setMaxMemory(long maxMemory);

    long getLastConnectionTime();

    long getUptime();

    void shutdown();

    void restart();

}
