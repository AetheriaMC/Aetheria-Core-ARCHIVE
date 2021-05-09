package net.badbird5907.aetheriacore.spigot.manager;

import net.badbird5907.aetheriacore.bungee.manager.log;
import net.badbird5907.aetheriacore.bungee.util.Config;
import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.utils.database.MySQL;
import net.md_5.bungee.config.Configuration;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager implements Manager {
    private static MySQL db;
    private static Connection connection;
    public static void connect(){
        db = new MySQL(AetheriaCore.getInstance().getConfig().getString("MySql.host"), AetheriaCore.getInstance().getConfig().getString("MySql.database"), AetheriaCore.getInstance().getConfig().getString("MySql.username"), AetheriaCore.getInstance().getConfig().getString("MySql.password") ,AetheriaCore.getInstance().getConfig().getString("MySql.port"), AetheriaCore.getInstance().getConfig().getBoolean("MySql.ssl"));
        try {
            if(db.connect()){
                log.Log("Connected to database!");
            }else log.Warn("An error occurred while connecting to the database!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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

    @Override
    public void onEnable(AetheriaCore plugin) {

    }

    @Override
    public void onDisable(AetheriaCore plugin) {

    }
}
