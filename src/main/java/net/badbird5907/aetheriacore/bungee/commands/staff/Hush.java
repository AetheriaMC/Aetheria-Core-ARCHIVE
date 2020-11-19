package net.badbird5907.aetheriacore.bungee.commands.staff;

import net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import static net.badbird5907.aetheriacore.bungee.manager.log.prefix;
import static net.badbird5907.aetheriacore.bungee.util.Permission.HUSH;
import static net.md_5.bungee.api.ChatColor.*;

public class Hush extends Command {
	public Hush() {
		super("hush", HUSH.node, "dontshowstaffstuff");
	}


	@Override
	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof ProxiedPlayer) {
			ProxiedPlayer player = (ProxiedPlayer) sender;
			if (sender.hasPermission(HUSH.node)) if (AetheriaCoreBungee.Hush.contains(player.getUniqueId())) {
				AetheriaCoreBungee.Hush.remove(player.getUniqueId());
				player.sendMessage(new TextComponent(prefix + WHITE + "Staff Alerts Toggled " + GREEN + "ON"));
			} else {
				AetheriaCoreBungee.Hush.add(player.getUniqueId());
				player.sendMessage(new TextComponent(prefix + WHITE + "Staff Alerts Toggled " + RED + "OFF"));
			}
		}
	}
}
