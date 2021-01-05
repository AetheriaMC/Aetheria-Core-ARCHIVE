package net.badbird5907.aetheriacore.spigot.commands;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;

import static java.util.Arrays.asList;
import static org.bukkit.Bukkit.getServer;
import static org.bukkit.ChatColor.*;

public class aetheriacore implements CommandExecutor {
	AetheriaCore plugin;

	public aetheriacore(AetheriaCore plugin) {
		this.plugin = plugin;
	}

	//method called on /aetheriacore
	@Override
	public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("aetheriacore")) {
			PluginDescriptionFile pdf = plugin.getDescription(); //Gets plugin.yml
			if (args.length == 0) {
				asList(DARK_GRAY + "------------------------------------", GOLD + "AetheriaCore Version " + pdf.getVersion(), GOLD + "Made By: Badbird5907", WHITE + "", WHITE + "The Aetheria Server's Core and API", WHITE + "use /aec help for a list of commands.", WHITE + "Connected Supported Plugins: ").forEach(player::sendMessage);
				//Only Anti Cheat Enabled
				if (getServer().getPluginManager().isPluginEnabled("AetheriaAntiCheat"))
					player.sendMessage(GREEN + "Aetheria-Anti-Cheat");
				//Only Minigames Enabled
				if (getServer().getPluginManager().isPluginEnabled("AetheriaMinigames"))
					player.sendMessage(GREEN + "Aetheria-Minigames");
				if (!getServer().getPluginManager().isPluginEnabled("AetheriaAntiCheat") && !getServer().getPluginManager().isPluginEnabled("AetheriaAntiCheat")) {
					asList(RED + "None", DARK_GRAY + "------------------------------------").forEach(player::sendMessage);
					return true;
					//all enabled
				}
				return true;
			}
			return true;
		}
		return true;
	}
}