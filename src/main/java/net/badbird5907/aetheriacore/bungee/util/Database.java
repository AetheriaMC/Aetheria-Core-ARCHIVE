package net.badbird5907.aetheriacore.bungee.util;

import jdk.internal.net.http.common.Log;
import net.badbird5907.aetheriacore.bungee.manager.log;
import net.md_5.bungee.command.ConsoleCommandSender;
import net.md_5.bungee.config.Configuration;
import org.bukkit.Bukkit;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    static Connection connection;
    private static Configuration config = Messages.getConfig("bungeeconfig");
    private static String uri = config.getString("Database.uri");
    private static String password = config.getString("Database.password");
    private static String username = config.getString("Database.username");
    private static String port = config.getString("Database.port");
    private static String database = config.getString("Database.database");
    // connect
    public static void connect() {
        if (!isConnected()) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://" + uri + ":" + port + "/" + database, username, password);
                net.badbird5907.aetheriacore.bungee.manager.log.Log(database + " Database Connected!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
    public static void SetupDB() throws SQLException {
        connect();
        PreparedStatement sc = Database.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS AetheriaCoreBungee_StaffChat(id varchar(36));");
        sc.executeUpdate();
        PreparedStatement ac = Database.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS AetheriaCoreBungee_AdminChat");
        ac.executeUpdate();
        PreparedStatement cspy = Database.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS AetheriaCoreBungee_CSPY");
        cspy.executeUpdate();
        PreparedStatement hush = Database.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS AetheriaCoreBungee_Hush");
        hush.executeUpdate();
        PreparedStatement last_login = Database.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS AetheriaCoreBungee_LastLogin");
        last_login.executeUpdate();
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
