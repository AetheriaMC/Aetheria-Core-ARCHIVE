package net.badbird5907.aetheriacore.spigot.commands.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static java.util.Arrays.asList;
import static net.badbird5907.aetheriacore.spigot.manager.permissionManager.ItemBlacklistList;
import static net.badbird5907.aetheriacore.spigot.manager.permissionManager.PermissionMessage;
import static org.bukkit.ChatColor.*;

public class itemblacklist implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
		if (player.hasPermission(ItemBlacklistList)) {
			asList(DARK_GRAY + "--------------------------------------------", GREEN + "" + BOLD + "Blacklisted Items:", GOLD + "Command_Block | Bedrock | Debug_Stick | Command_Block_Minecart", GOLD + "Repeating_Command_Block | Barrier | Structure_Block ", GOLD + "Spawner | Jigsaw", GREEN + "To Bypass This, The Player Must Have OP or The Permission Node:", RED + "'aetheriacore.bypass.blacklistitems'" + DARK_GRAY + " " + ITALIC + "BLACKLISTED_ITEM_DETECTED", DARK_GRAY + "--------------------------------------------").forEach(player::sendMessage);
			return true;
		}
		player.sendMessage(PermissionMessage);
		return true;
	}
}