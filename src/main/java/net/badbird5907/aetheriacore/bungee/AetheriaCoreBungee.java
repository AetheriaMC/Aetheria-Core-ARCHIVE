package net.badbird5907.aetheriacore.bungee;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import net.badbird5907.aetheriacore.bungee.commands.staff.*;
import net.badbird5907.aetheriacore.bungee.commands.util.GlobalBroadcast;
import net.badbird5907.aetheriacore.bungee.commands.util.GlobalClearChat;
import net.badbird5907.aetheriacore.bungee.commands.warps.*;
import net.badbird5907.aetheriacore.bungee.events.*;
import net.badbird5907.aetheriacore.bungee.manager.log;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class AetheriaCoreBungee extends Plugin implements Listener {
    public static List<UUID> StaffChatPlayers = new ArrayList<>();
    public static List<UUID> AdminChatPlayers = new ArrayList<>();
    public static List<UUID> CommandSpyPlayers = new ArrayList<>();
    public static List<UUID> Hush = new ArrayList<>();
    private static AetheriaCoreBungee instance;
    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        log.Log("Starting...");
        //getProxy().registerChannel("aetheriacore:messaging");
        log.Log( "Registering Commands...");
        getProxy().getInstance().getPluginManager().registerCommand(this, new Hub());
        getProxy().getInstance().getPluginManager().registerCommand(this, new Beta());
        getProxy().getInstance().getPluginManager().registerCommand(this, new Creative());
        getProxy().getInstance().getPluginManager().registerCommand(this, new Survival());
        getProxy().getInstance().getPluginManager().registerCommand(this, new Vanilla());
        getProxy().getInstance().getPluginManager().registerCommand(this, new CommandSpy());
        getProxy().getInstance().getPluginManager().registerCommand(this, new Hush());
        getProxy().getInstance().getPluginManager().registerCommand(this, new staff());
        getProxy().getInstance().getPluginManager().registerCommand(this, new StaffChat());
        getProxy().getInstance().getPluginManager().registerCommand(this, new AdminChat());
        getProxy().getInstance().getPluginManager().registerCommand(this, new GlobalClearChat());
        getProxy().getInstance().getPluginManager().registerCommand(this, new GlobalBroadcast());
        log.Log("Registering Events...");
        getProxy().getInstance().getPluginManager().registerListener(this, new OnLogin());
        getProxy().getInstance().getPluginManager().registerListener(this, new CommandListener());
        getProxy().getInstance().getPluginManager().registerListener(this, new OnDisconnect());
        getProxy().getInstance().getPluginManager().registerListener(this, new OnSwitch());
        log.Log("Startup Finished!!!");

    }

/*
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
*/
    private void setupDatabase(){
        MongoClient mongoClient = MongoClients.create("mongodb+srv://AetheriaCorePlugin:AetheriaCorePlugin@aetheriacore-db1.jyi3w.gcp.mongodb.net/AetheriaCore-DB1?retryWrites=true&w=majority");
        //MongoCollection<Document> toggles = mongoClient.getDatabase("AetheriaCore-DB1").getCollection("toggles");
        MongoDatabase database = mongoClient.getDatabase("users");
    }
    public static AetheriaCoreBungee getInstance() {
        return instance;
    }

}


