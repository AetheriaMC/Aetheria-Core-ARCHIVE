package net.badbird5907.aetheriacore.spigot.events;

import de.myzelyam.api.vanish.VanishAPI;
import net.badbird5907.aetheriacore.spigot.manager.pluginManager;
import net.badbird5907.aetheriacore.spigot.util.NPC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerJoinEvent implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void JoinEvent(PlayerLoginEvent event){
        Player player = event.getPlayer();
        if(NPC.getNPCs() == null)
            return;
        if (NPC.getNPCs().isEmpty())
            return;
        else {
            NPC.addJoinPacket(event.getPlayer());
        }
        if(VanishAPI.getAllInvisiblePlayers().contains(player)){
            if(pluginManager.OnlineVisiblePlayers.contains(event.getPlayer().getName())){
                pluginManager.warn(pluginManager.prefix + "Array List \"OnlineVisiblePlayers\" already contains " + event.getPlayer().getDisplayName() + "please report this to Badbird.");
            }
            else{
                pluginManager.OnlineVisiblePlayers.add(event.getPlayer().getName());
            }
        }
        else{
            pluginManager.VanishedPlayers.add(player.getName());
            pluginManager.OnlinePlayers.add(player.getName());
            pluginManager.warn(player.getDisplayName() + " is vanished.");
        }
        /*MongoClient mongoClient = MongoClients.create("mongodb+srv://AetheriaCorePlugin:AetheriaCorePlugin@aetheriacore-db1.jyi3w.gcp.mongodb.net/AetheriaCore-DB1?retryWrites=true&w=majority");
        MongoDatabase database = mongoClient.getDatabase("users");
        MongoCollection<Document> coll = database.getCollection(String.valueOf(player.getUniqueId()));
         */


    }
}
