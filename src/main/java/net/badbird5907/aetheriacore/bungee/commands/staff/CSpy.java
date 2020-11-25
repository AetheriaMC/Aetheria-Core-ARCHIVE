package net.badbird5907.aetheriacore.bungee.commands.staff;


import net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee;
import net.badbird5907.aetheriacore.bungee.util.Permission;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;

public class CSpy extends Command {
    public CSpy() {
        super("cspy", Permission.COMMAND_SPY.node, new String[] { "commandspy" });
    }

    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer)sender;
            Configuration config = AetheriaCoreBungee.getInstance().getConfig("bungeeconfig");
            if (args.length == 0) {
                if (p.hasPermission(Permission.COMMAND_SPY.node)) {
                    if (AetheriaCoreBungee.inCSpy.contains(p.getUniqueId())) {
                        AetheriaCoreBungee.inCSpy.remove(p.getUniqueId());
                        p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.cspy-disabled"))));
                    } else {
                        AetheriaCoreBungee.inCSpy.add(p.getUniqueId());
                        p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.cspy-enabled"))));
                    }
                } else {
                    p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.no-permission"))));
                }
            }
        }
    }
}

