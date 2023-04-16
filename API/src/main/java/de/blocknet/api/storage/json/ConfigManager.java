package de.blocknet.api.storage.json;

import de.blocknet.api.storage.json.impl.IConfig;
import lombok.SneakyThrows;

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

    public IConfig getIModule(Class<? extends IConfig> iModule) {
        return iConfigs.stream().filter(iModules -> iModules.getClass().getName().equals(iModule.getName())).findAny().orElse(null);
    }

    public boolean isExistsIModule(IConfig iModule) {
        return getIModule(iModule.getClass()) != null;
    }

    @SneakyThrows
    public void addModule(IConfig iModule, boolean saveDefaultConfig) {
        if (!iModule.getFile().getParentFile().exists()) iModule.getFile().getParentFile().mkdirs();
        if (!iModule.getFile().exists()) {
            iModule.getFile().createNewFile();
            if (saveDefaultConfig) {
                FileWriter writer = new FileWriter(iModule.getFile());
                writer.write(iModule.getDefaultConfig());
                writer.flush();
            }
        }
        this.iConfigs.add(iModule.fromJson(getContent(iModule)));
    }

    public void removeIModule(IConfig iModule) {
        if (isExistsIModule(iModule)) {
            iConfigs.remove(iModule);
            iModule.getFile().delete();
        }
    }

    @SneakyThrows
    public void insert(IConfig iModule, String data) {
        FileWriter fileWriter = new FileWriter(iModule.getFile());
        fileWriter.write(data);
        fileWriter.flush();
        fileWriter.close();
    }

    @SneakyThrows
    public String getContent(IConfig iModule) {
        return new String(Files.readAllBytes(iModule.getFile().toPath()));
    }

    @SneakyThrows
    public String getContent(File file) {
        return new String(Files.readAllBytes(file.toPath()));
    }
}
