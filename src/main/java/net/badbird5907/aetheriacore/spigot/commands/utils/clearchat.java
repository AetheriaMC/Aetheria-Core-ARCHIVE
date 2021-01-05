package net.badbird5907.aetheriacore.spigot.commands.utils;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static java.util.Arrays.asList;
import static java.util.stream.IntStream.range;
import static net.badbird5907.aetheriacore.spigot.manager.PluginManager.log;
import static net.badbird5907.aetheriacore.spigot.manager.permissionManager.*;
import static org.bukkit.Bukkit.getOnlinePlayers;
import static org.bukkit.ChatColor.*;

public class clearchat implements CommandExecutor {

	AetheriaCore plugin;

	public clearchat(AetheriaCore plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			if (command.getName().equalsIgnoreCase("clearchat"))
				if (args.length == 0) if (sender.hasPermission(ClearChat)) method(sender);
				else {
					sender.sendMessage(PermissionMessage);
					log(sender.getName() + " Was denied access to the command.");
				}
				else if (sender.hasPermission(ClearChat)) {
					method(sender);
				}
		} else getOnlinePlayers().forEach(player -> {
			if (player.hasPermission(ClearChat)) player.sendMessage(GREEN + "Chat was cleared but you are immune!");
			else {
				range(0, 300).mapToObj(x -> DARK_GRAY + "").forEach(player::sendMessage);
				player.sendMessage(GREEN + "Chat Was Cleared By " + RED + BOLD + "Console" + GREEN + "!");
			}
		});
		return true;
	}

	private void method(CommandSender sender) {
		for (Player player : getOnlinePlayers()) {
			if (player.hasPermission(BypassClearChat))
				asList(GREEN + "Chat was cleared but you are immune!", GREEN + sender.getName() + " Was the player who cleared the chat.").forEach(player::sendMessage);
			else {
				range(0, 300).mapToObj(x -> RED + "     ").forEach(player::sendMessage);
				asList(BLUE + "" + BOLD + "-------------------------------------------------------", GREEN + "Chat Was Cleared By" + RED + BOLD + player.getDisplayName() + GREEN + "!").forEach(player::sendMessage);
				player.sendMessage(BLUE + "" + BOLD + "-------------------------------------------------------");
			}
		}
	}
}