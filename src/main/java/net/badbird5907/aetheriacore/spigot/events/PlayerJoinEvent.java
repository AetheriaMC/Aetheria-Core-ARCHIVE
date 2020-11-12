package net.badbird5907.aetheriacore.spigot.events;

import de.myzelyam.api.vanish.VanishAPI;
import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import net.badbird5907.aetheriacore.spigot.manager.pluginManager;
import net.badbird5907.aetheriacore.spigot.util.NPC;
import net.md_5.bungee.protocol.packet.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerJoinEvent implements Listener {
    AetheriaCore plugin;
    public PlayerJoinEvent(AetheriaCore plugin) {
        this.plugin = plugin;
    }

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
        int Visible_Players = plugin.getServer().getOnlinePlayers().toArray().length-VanishAPI.getInvisiblePlayers().toArray().length;
        player.sendMessage(ChatColor.WHITE + "-----------------------------------");
        player.sendMessage(ChatColor.GOLD + "");
        player.sendMessage(ChatColor.GOLD + "Welcome To Aetheria!");
        player.sendMessage(ChatColor.GOLD + "You are connected to the " + plugin.getConfig().getString("Server-Type") + " Server!");
        player.sendMessage(ChatColor.GOLD + "Please Read the rules! " + ChatColor.GREEN + "" + ChatColor.BOLD + "(/rules)");
        player.sendMessage(ChatColor.GOLD + "There Are " + Visible_Players + " Online");
        if(player.hasPermission(permissionManager.SeeVanishedPlayers))
            player.sendMessage(ChatColor.GREEN + "There Are " + VanishAPI.getInvisiblePlayers().toArray().length + " Online VANISHED Players.");
        player.sendMessage("");
        player.sendMessage(ChatColor.WHITE + "-----------------------------------");
        /*MongoClient mongoClient = MongoClients.create("mongodb+srv://AetheriaCorePlugin:AetheriaCorePlugin@aetheriacore-db1.jyi3w.gcp.mongodb.net/AetheriaCore-DB1?retryWrites=true&w=majority");
        MongoDatabase database = mongoClient.getDatabase("users");
        MongoCollection<Document> coll = database.getCollection(String.valueOf(player.getUniqueId()));
         */


    }
}
