package de.blocknet.api.storage.mysql.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MySQLConfig {

    private String host;
    private Integer port;
    private String database;
    private String user;
    private String password;


}
