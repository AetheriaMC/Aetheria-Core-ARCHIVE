package net.badbird5907.aetheriacore.spigot.events;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.manager.databaseManager;
import org.bson.Document;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class joinListener implements Listener {
    AetheriaCore plugin;
    public joinListener(AetheriaCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void joinListener (PlayerJoinEvent event){
        Player player = event.getPlayer();
        MongoClient mongoClient = MongoClients.create("mongodb+srv://AetheriaCorePlugin:AetheriaCorePlugin@aetheriacore-db1.jyi3w.gcp.mongodb.net/AetheriaCore-DB1?retryWrites=true&w=majority");
        MongoDatabase database = mongoClient.getDatabase("users");
        MongoCollection<Document> coll = database.getCollection(String.valueOf(player.getUniqueId()));
    }


}
