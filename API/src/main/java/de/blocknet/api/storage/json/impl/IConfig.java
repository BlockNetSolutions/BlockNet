package de.blocknet.api.storage.json.impl;

import java.io.File;

public interface IConfig {

    File getFile();

    String toJson();

    IConfig fromJson(String data);

    String getDefaultConfig();
}
