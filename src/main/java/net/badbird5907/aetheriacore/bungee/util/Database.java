package net.badbird5907.aetheriacore.bungee.util;

import net.badbird5907.aetheriacore.bungee.manager.log;
import net.md_5.bungee.config.Configuration;

import java.sql.*;

public class Database {
    static Connection connection;
    private static Configuration config = Messages.getConfig("bungeeconfig");
    private static String uri = config.getString("Database.uri");
    private static String password = config.getString("Database.password");
    private static String username = config.getString("Database.username");
    private static String port = config.getString("Database.port");
    private static String database = config.getString("Database.database");
    // connect
    /*
    public static void connect() {
        if (!isConnected()) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://" + uri + ":" + port + "/" + database ,username, password);
                log.Log("Connecting to jdbc:mysql://" + uri + ":" + port + "/" + database + "" + username + "" + password);
                if(isConnected())
                    log.Log("Database Connected!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
     */
    public static void connect() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://"
                        + uri+ ":" + port + "/" + database,
                username, password);
        log.Log("Connecting to jdbc:mysql://" + uri + ":" + port + "/" + database + "" + username + "" + password);
    }
    public static void disconnect(){
        if(isConnected()){
            try {
                net.badbird5907.aetheriacore.bungee.manager.log.Log("Disconnecting from database...");
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    // isConnected
    public static boolean isConnected() {
        return (connection == null ? false : true);
    }

    // getConnection
    public static Connection getConnection() {
        return connection;
    }
    public static void SetupDB() throws SQLException {
        try {
            connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        PreparedStatement staff = Database.getConnection().prepareStatement( "CREATE TABLE IF NOT EXISTS AetheriaCoreBungee_Staff (uuid varchar(36), sc BOOLEAN, ac BOOLEAN, cspy BOOLEAN, hush BOOLEAN);");
        staff.executeUpdate();
        PreparedStatement players = Database.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS AetheriaCoreBungee_PlayerData (uuid varchar(36), name varchar(16), last_seen varchar(8), online BOOLEAN);");
        players.executeUpdate();
    }
    public static boolean TableExists(String name) throws SQLException {
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet tables = dbm.getTables(null, null, name, null);
        if (tables.next()) {
            return true;
        }
        else {
            return false;
        }
    }
}
