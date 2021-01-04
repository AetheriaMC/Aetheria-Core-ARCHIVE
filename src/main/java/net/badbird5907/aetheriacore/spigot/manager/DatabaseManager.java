package net.badbird5907.aetheriacore.spigot.manager;

import net.badbird5907.aetheriacore.bungee.manager.log;
import net.badbird5907.aetheriacore.bungee.util.Config;
import net.badbird5907.aetheriacore.utils.database.MySQL;
import net.md_5.bungee.config.Configuration;

import java.sql.Connection;

public class DatabaseManager {
    private static MySQL db;
    private static Connection connection;
    public static void connect(){
//        db = new MySQL(config.getString("MySql.host"), config.getString("MySql.database"), config.getString("MySql.username"), config.getString("MySql.password") ,config.getInt("MySql.port"), config.getBoolean("MySql.ssl"));
        if(db.openConnection()){
            log.Log("Connected to database!");
        }else log.Warn("An error occurred while connecting to the database!");
        connection = db.getConnection();
    }
    public static void disconnect(){
        db.closeConnection();
    }
    public static Connection getConnection(){
        return connection;
    }
    public static MySQL getDB(){
        return db;
    }
}
