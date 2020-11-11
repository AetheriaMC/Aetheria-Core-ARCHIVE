package net.badbird5907.aetheriacore.bungee;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import net.badbird5907.aetheriacore.bungee.commands.warps.*;
import net.badbird5907.aetheriacore.bungee.manager.log;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

public final class AetheriaCoreBungee extends Plugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        log.Log("Starting...");
        getProxy().registerChannel("aetheriacore:messaging");
        log.Log( "Registering Commands...");
        getProxy().getPluginManager().registerCommand(this, new Hub());
        getProxy().getInstance().getPluginManager().registerCommand(this, new Beta());
        getProxy().getInstance().getPluginManager().registerCommand(this, new Creative());
        getProxy().getInstance().getPluginManager().registerCommand(this, new Survival());
        getProxy().getInstance().getPluginManager().registerCommand(this, new Vanilla());

        log.Log("Registering Events...");
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
}


