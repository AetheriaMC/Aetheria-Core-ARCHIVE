package net.badbird5907.aetheriacore.bungee.commands.staff;

import net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee;
import net.badbird5907.aetheriacore.bungee.manager.log;
import net.badbird5907.aetheriacore.bungee.util.Permission;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CommandSpy extends Command {
    public CommandSpy() {
        super("commandspy", Permission.COMMAND_SPY.node, new String[] { "cspy" });
    }

    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer)sender;
            if (args.length == 0) {
                if (p.hasPermission(Permission.COMMAND_SPY.node)) {
                    if (AetheriaCoreBungee.CommandSpyPlayers.contains(p.getUniqueId())) {
                        AetheriaCoreBungee.CommandSpyPlayers.remove(p.getUniqueId());
                        p.sendMessage(new TextComponent(log.permissionmessage + ChatColor.WHITE + "Command Spy " + ChatColor.RED + "OFF"));
                    } else {
                        AetheriaCoreBungee.CommandSpyPlayers.add(p.getUniqueId());
                        p.sendMessage(new TextComponent(log.permissionmessage + ChatColor.WHITE + "Command Spy " + ChatColor.GREEN + "ON"));
                    }
                } else {
                    p.sendMessage(new TextComponent(log.permissionmessage));
                }
            }
        }
    }
}
