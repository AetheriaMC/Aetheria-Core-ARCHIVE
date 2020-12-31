package net.badbird5907.aetheriacore.bungee.commands.staff;

import net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee;
import net.badbird5907.aetheriacore.bungee.api.SendStaffChatMessage;
import net.badbird5907.aetheriacore.bungee.util.Messages;
import net.badbird5907.aetheriacore.bungee.util.Permission;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;

public class StaffChat extends Command {
	public StaffChat() {
		super("sc", Permission.STAFF_CHAT.node, new String[] { "staffchat" });
	}

	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer)sender;
			Configuration config = Messages.getConfig("bungeemessages");
			if (args.length == 0) {
				if (p.hasPermission(Permission.STAFF_CHAT.node)) {
					if(AetheriaCoreBungee.inAc.contains(p.getUniqueId())){
						AetheriaCoreBungee.inAc.remove(p.getUniqueId());
						p.sendMessage(new TextComponent(
								ChatColor.GREEN + "You were in Admin chat so Admin chat was toggled off"
						));
					}
					if (AetheriaCoreBungee.inSc.contains(p.getUniqueId())) {
						p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.sc-disabled"))));
						AetheriaCoreBungee.inSc.remove(p.getUniqueId());
					} else {
						AetheriaCoreBungee.inSc.add(p.getUniqueId());
						p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.sc-enabled"))));
					}
				} else {
					p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.no-permission"))));
				}
			} else if (p.hasPermission(Permission.STAFF_CHAT.node)) {
				String msg = "";
				for (int i = 0; i < args.length; i++)
					msg = msg + args[i] + " ";
				SendStaffChatMessage.Send(p, msg);
			} else {
				p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.no-permission"))));
			}
		}
	}
}
