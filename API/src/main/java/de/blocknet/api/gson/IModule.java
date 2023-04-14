package de.blocknet.api.gson;

import java.io.File;

public interface IModule {

    File getFile();

    String toJson();

    IModule fromJson(String data);

    String getDefaultConfig();
}
