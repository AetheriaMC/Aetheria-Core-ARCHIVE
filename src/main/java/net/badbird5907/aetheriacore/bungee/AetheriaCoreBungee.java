package net.badbird5907.aetheriacore.bungee;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import jdk.internal.org.jline.utils.Log;
import net.badbird5907.aetheriacore.bungee.commands.staff.*;
import net.badbird5907.aetheriacore.bungee.commands.util.GlobalBroadcast;
import net.badbird5907.aetheriacore.bungee.commands.warps.*;
import net.badbird5907.aetheriacore.bungee.listeners.LockdownListener;
import net.badbird5907.aetheriacore.bungee.listeners.events;
import net.badbird5907.aetheriacore.bungee.manager.log;
import net.badbird5907.aetheriacore.bungee.util.Config;
import net.badbird5907.aetheriacore.bungee.util.DataFile;
import net.badbird5907.aetheriacore.bungee.util.Database;
import net.badbird5907.aetheriacore.bungee.util.Messages;
import net.badbird5907.aetheriacore.spigot.util.Data;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class AetheriaCoreBungee extends Plugin implements Listener {
    public static List<UUID> inSc = new ArrayList<>();
    public static List<UUID> inAc = new ArrayList<>();
    public static List<UUID> inCSpy = new ArrayList<>();
    public static List<UUID> Hush = new ArrayList<>();
    public static Boolean is_lockdown;
    private static AetheriaCoreBungee instance;


    public static AetheriaCoreBungee getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        log.Log("Starting...");
        //getProxy().registerChannel("aetheriacore:messaging");
        Messages.createFile("bungeemessages");
        DataFile.createFile("bungeedata");
        Config.createFile("bungeeconfig");
        log.Log("Registering Commands...");
        getProxy().getInstance().getPluginManager().registerCommand(this, new Hub());
        getProxy().getInstance().getPluginManager().registerCommand(this, new Beta());
        getProxy().getInstance().getPluginManager().registerCommand(this, new Creative());
        getProxy().getInstance().getPluginManager().registerCommand(this, new Survival());
        getProxy().getInstance().getPluginManager().registerCommand(this, new Vanilla());

        getProxy().getInstance().getPluginManager().registerCommand(this, new AdminChat());
        getProxy().getInstance().getPluginManager().registerCommand(this, new CSpy());
        getProxy().getInstance().getPluginManager().registerCommand(this, new GlobalBroadcast());
        getProxy().getInstance().getPluginManager().registerCommand(this, new GlobalClearChat());
        getProxy().getInstance().getPluginManager().registerCommand(this, new StaffChat());
        getProxy().getInstance().getPluginManager().registerCommand(this, new staff());

        log.Log("Registering Events...");
        getProxy().getPluginManager().registerListener(this, new events());
        getProxy().getPluginManager().registerListener(this, new LockdownListener());
        /*
        getProxy().getInstance().getPluginManager().registerListener(this, new OnLogin());
        getProxy().getInstance().getPluginManager().registerListener(this, new CommandListener());
        getProxy().getInstance().getPluginManager().registerListener(this, new OnDisconnect());
        getProxy().getInstance().getPluginManager().registerListener(this, new OnSwitch());
         */
        Configuration config = Messages.getConfig("bungeemessages");
        is_lockdown = config.getBoolean("Data.lockdown");
        log.Log("Connecting to database");
        try {
            Database.SetupDB();
        } catch (SQLException throwables) {
            Log.error("Could not connect to database!");
            throwables.printStackTrace();
        }
        log.Log("Startup Finished!!!");

    }
        @Override
        public void onDisable() {
            Database.disconnect();
        }

    private void setupDatabase() {
        MongoClient mongoClient = MongoClients.create("mongodb+srv://AetheriaCorePlugin:AetheriaCorePlugin@aetheriacore-db1.jyi3w.gcp.mongodb.net/AetheriaCore-DB1?retryWrites=true&w=majority");
        //MongoCollection<Document> toggles = mongoClient.getDatabase("AetheriaCore-DB1").getCollection("toggles");
        MongoDatabase database = mongoClient.getDatabase("users");
    }

}


