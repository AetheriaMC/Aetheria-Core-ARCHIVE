package net.badbird5907.aetheriacore.spigot.events;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import static de.myzelyam.api.vanish.VanishAPI.getAllInvisiblePlayers;
import static de.myzelyam.api.vanish.VanishAPI.getInvisiblePlayers;
import static java.util.Arrays.asList;
import static net.badbird5907.aetheriacore.spigot.manager.PluginManager.*;
import static net.badbird5907.aetheriacore.spigot.util.NPC.addJoinPacket;
import static net.badbird5907.aetheriacore.spigot.util.NPC.getNPCs;
import static org.bukkit.ChatColor.*;
import static org.bukkit.event.EventPriority.MONITOR;

public class PlayerJoinEvent implements Listener {
	AetheriaCore plugin;

	public PlayerJoinEvent(AetheriaCore plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = MONITOR)
	public void JoinEvent(PlayerLoginEvent event) {
		Player player = event.getPlayer();
		if (getNPCs() == null) return;
		else if (getNPCs().isEmpty()) return;
		addJoinPacket(event.getPlayer());
		if (getAllInvisiblePlayers().contains(player)) if (OnlineVisiblePlayers.contains(event.getPlayer().getName()))
			warn(prefix + "Array List \"OnlineVisiblePlayers\" already contains " + event.getPlayer().getDisplayName() + "please report this to Badbird.");
		else OnlineVisiblePlayers.add(event.getPlayer().getName());
		else {
			VanishedPlayers.add(player.getName());
			OnlinePlayers.add(player.getName());
			warn(player.getDisplayName() + " is vanished.");
		}
		int Visible_Players = plugin.getServer().getOnlinePlayers().toArray().length - getInvisiblePlayers().toArray().length;
		asList(WHITE + "-----------------------------------", GOLD + "", GOLD + "Welcome To Aetheria!", GOLD + "You are connected to the " + plugin.getConfig().getString("Server-Type") + " Server!", GOLD + "Please Read the rules! " + GREEN + "" + BOLD + "(/rules)", GOLD + "There Are " + Visible_Players + " Online").forEach(player::sendMessage);
		if (player.hasPermission(permissionManager.SeeVanishedPlayers))
			player.sendMessage(GREEN + "There Are " + getInvisiblePlayers().toArray().length + " Online VANISHED Players.");
		player.sendMessage("");
		player.sendMessage(WHITE + "-----------------------------------");
        /*MongoClient mongoClient = MongoClients.create("mongodb+srv://AetheriaCorePlugin:AetheriaCorePlugin@aetheriacore-db1.jyi3w.gcp.mongodb.net/AetheriaCore-DB1?retryWrites=true&w=majority");
        MongoDatabase database = mongoClient.getDatabase("users");
        MongoCollection<Document> coll = database.getCollection(String.valueOf(player.getUniqueId()));
         */
	}
}