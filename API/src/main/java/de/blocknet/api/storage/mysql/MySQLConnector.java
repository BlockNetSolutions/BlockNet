package de.blocknet.api.storage.mysql;

import de.blocknet.api.storage.mysql.impl.MySQLConfig;

import java.sql.*;

public class MySQLConnector {

    private Connection connection;
    private final MySQLConfig mySQLConfig;
    public MySQLConnector(MySQLConfig mySQLConfig) {
        this.mySQLConfig = mySQLConfig;
    }

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + mySQLConfig.getHost() + ":" + mySQLConfig.getPort() + "/" + mySQLConfig.getDatabase() + "?autoReconnect=true", mySQLConfig.getUser(), mySQLConfig.getPassword());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(String qry, Object... objects) {
        try {
            PreparedStatement ps = connection.prepareStatement(qry);
            for (int i = 0; i < objects.length; i++) {
                ps.setObject(i + 1, objects[i]);
            }
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            connect();
            System.err.println(e);
        }
    }

    public ResultSet query(String qry, Object... objects) {
        ResultSet rs = null;
        try {
            PreparedStatement ps = connection.prepareStatement(qry);
            for (int i = 0; i < objects.length; i++) {
                ps.setObject(i + 1, objects[i]);
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            connect();
            System.err.println(e);
        }
        return rs;
    }

    public boolean isConnected() {
        try {
            return connection.isValid(1);
        } catch (SQLException e) {
            return false;
        }
    }
}