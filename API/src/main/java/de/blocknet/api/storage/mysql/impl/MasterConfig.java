package de.blocknet.api.storage.mysql.impl;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.blocknet.api.storage.json.impl.IConfig;
import lombok.AllArgsConstructor;

import java.io.File;

@AllArgsConstructor
public class MasterConfig implements IConfig {

    private MySQLConfig MySQL;


    @Override
    public File getFile() {
        return new File(System.getProperty("user.dir") + "/config.json");
    }

    @Override
    public String toJson() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }

    @Override
    public IConfig fromJson(String data) {
        return new Gson().fromJson(data, MasterConfig.class);
    }

    @Override
    public String getDefaultConfig() {
        return new MasterConfig(new MySQLConfig("test",1,"t","ddd","sdfdfsafds")).toJson();
    }
}
