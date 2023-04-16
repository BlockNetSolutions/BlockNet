package de.blocknet.api.storage.mysql.impl;

import lombok.Getter;

@Getter
public class MySQLConfig {

    private String host;
    private Integer port;
    private String database;
    private String user;
    private String password;
}
