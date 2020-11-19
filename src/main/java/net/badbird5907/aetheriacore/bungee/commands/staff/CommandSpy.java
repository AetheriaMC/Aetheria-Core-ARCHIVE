package net.badbird5907.aetheriacore.bungee.commands.staff;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import static net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee.CommandSpyPlayers;
import static net.badbird5907.aetheriacore.bungee.manager.log.permissionmessage;
import static net.badbird5907.aetheriacore.bungee.util.Permission.COMMAND_SPY;
import static net.md_5.bungee.api.ChatColor.*;

public class CommandSpy extends Command {
	public CommandSpy() {
		super("commandspy", COMMAND_SPY.node, "cspy");
	}

	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if (args.length == 0)
				if (p.hasPermission(COMMAND_SPY.node)) if (CommandSpyPlayers.contains(p.getUniqueId())) {
					CommandSpyPlayers.remove(p.getUniqueId());
					p.sendMessage(new TextComponent(permissionmessage + WHITE + "Command Spy " + RED + "OFF"));
				} else {
					CommandSpyPlayers.add(p.getUniqueId());
					p.sendMessage(new TextComponent(permissionmessage + WHITE + "Command Spy " + GREEN + "ON"));
				}
				else p.sendMessage(new TextComponent(permissionmessage));
		}
	}
}
