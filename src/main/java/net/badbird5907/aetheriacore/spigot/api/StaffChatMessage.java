package net.badbird5907.aetheriacore.spigot.api;

import net.badbird5907.aetheriacore.spigot.commands.utils.hush;
import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import org.bukkit.entity.Player;

import static net.badbird5907.aetheriacore.spigot.manager.pluginManager.log;
import static org.bukkit.Bukkit.getOnlinePlayers;
import static org.bukkit.ChatColor.*;

/**
 * The type Staff chat message.
 */
public class StaffChatMessage {
	/**
	 * Sendmessage.
	 *
	 * @param sender  Shows the sender of the message
	 * @param message The message
	 */
	public static void sendMessage(String sender, String message) {
		for (Player player : getOnlinePlayers())
			if (player.hasPermission(permissionManager.staffchat)) {
				if (hush.hush.contains(player.getUniqueId())) break;
				player.sendMessage(GOLD + "StaffChat" + DARK_GRAY + " » " + RESET + sender + ": " + message);
				log("StaffChat » " + sender + ": " + message);
			} else break;
	}
}
