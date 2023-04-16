package de.blocknet.api.storage.json;

import de.blocknet.api.storage.json.impl.IConfig;
import lombok.SneakyThrows;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class ConfigManager {

    private List<IConfig> iConfigs;

    public ConfigManager() {
        this.iConfigs = new ArrayList<>();
    }

    public IConfig getIModule(Class<? extends IConfig> iConfig) {
        return iConfigs.stream().filter(iModules -> iModules.getClass().getName().equals(iConfig.getName())).findAny().orElse(null);
    }

    public boolean isExistsIModule(IConfig iConfig) {
        return getIModule(iConfig.getClass()) != null;
    }

    @SneakyThrows
    public void addModule(IConfig iConfig, boolean saveDefaultConfig) {
        if(iConfig.getFile().getParentFile().exists()) {
            if (!iConfig.getFile().getParentFile().exists()) iConfig.getFile().getParentFile().mkdirs();
        }
        if (!iConfig.getFile().exists()) {
            iConfig.getFile().createNewFile();
            if (saveDefaultConfig) {
                FileWriter writer = new FileWriter(iConfig.getFile());
                writer.write(iConfig.getDefaultConfig());
                writer.flush();
            }
        }
        this.iConfigs.add(iConfig.fromJson(getContent(iConfig)));
    }

    public void removeIModule(IConfig iConfig) {
        if (isExistsIModule(iConfig)) {
            iConfigs.remove(iConfig);
            iConfig.getFile().delete();
        }
    }

    @SneakyThrows
    public void insert(IConfig iConfig, String data) {
        FileWriter fileWriter = new FileWriter(iConfig.getFile());
        fileWriter.write(data);
        fileWriter.flush();
        fileWriter.close();
    }

    @SneakyThrows
    public String getContent(IConfig iConfig) {
        return new String(Files.readAllBytes(iConfig.getFile().toPath()));
    }

    @SneakyThrows
    public String getContent(File file) {
        return new String(Files.readAllBytes(file.toPath()));
    }
}
