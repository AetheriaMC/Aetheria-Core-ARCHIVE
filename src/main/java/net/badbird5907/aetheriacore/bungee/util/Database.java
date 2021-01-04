package net.badbird5907.aetheriacore.bungee.util;

import net.badbird5907.aetheriacore.bungee.manager.log;
import net.badbird5907.aetheriacore.utils.database.MySQL;
import net.md_5.bungee.config.Configuration;

import java.sql.*;

public class Database {
    static Connection connection;
    private static Configuration config = Messages.getConfig("bungeeconfig");
    private static String uri = config.getString("Database.uri");
    private static String password = config.getString("Database.password");
    private static String username = config.getString("Database.username");
    private static String port = config.getString("Database.port");
    private static String database = config.getString("Database.name");

    private static MySQL db;

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
        /*
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://"
                        + uri+ ":" + port + "/" + database,
                username, password);
        log.Log("Connecting to jdbc:mysql://" + uri + ":" + port + "/" + database + " " + username + " " + password);
        */
        db = new MySQL(uri, database, username, password, port, true);
        if(db.connect()){
            connection = db.getConnection();
            log.Log("Connected to database!");
        }
        else{
            log.Warn("ERROR: Could not connect to database!");
        }
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
    public static boolean TableExists(String name) {
        try {
            DatabaseMetaData dbm = connection.getMetaData();
            ResultSet tables = dbm.getTables(null, null, name, null);
            if (tables.next()) {
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
    public static ResultSet ExecuteQuery(String sql){
        try {
            PreparedStatement a = Database.getConnection().prepareStatement(sql);
            return a.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
    public static MySQL getDb(){
        return db;
    }
}
