package net.badbird5907.aetheriacore.bungee.listeners;

import net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee;
import net.badbird5907.aetheriacore.bungee.util.DataFile;
import net.badbird5907.aetheriacore.bungee.util.Permission;
import net.badbird5907.aetheriacore.bungee.util.PlayerHandler;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.event.EventHandler;

public class CommandListener implements Listener {
    @EventHandler
    public void Check(ChatEvent e) {
        ProxiedPlayer p = (ProxiedPlayer) e.getSender();
        Configuration datafile = DataFile.getData("bungeedata");
        if (e.getMessage().equalsIgnoreCase("/lockdown HIGH")) {
            if (p.hasPermission(Permission.GLOBAL_LOCKDOWN.node)) {
                if (datafile.getBoolean("Lockdown.enabled")) {
                    datafile.set("Lockdown.enabled", false);
                    DataFile.saveData(datafile, "bungeedata");
                } else {
                    p.sendMessage(new TextComponent(

                    ));
                }
            }
        }
        if (e.getMessage().startsWith("/") && !e.getMessage().startsWith("/commandspy") && !e.getMessage().startsWith("/cspy")) {
            if (p.hasPermission(Permission.COMMAND_SPY_BYPASS.node)) {
                for (ProxiedPlayer staff : BungeeCord.getInstance().getPlayers()) {
                    if (AetheriaCoreBungee.inCSpy.contains(staff.getUniqueId()))
                        staff.sendMessage(
                                new TextComponent(ChatColor.translateAlternateColorCodes('&', datafile.getString("Messages.cspy-format"))
                                        .replaceAll("%server%", p.getServer().getInfo().getName())
                                        .replaceAll("%user%", p.getName())
                                        .replaceAll("%command%", e.getMessage())
                                        .replaceAll("%player%", PlayerHandler.playerwithrank(p))));
                }
                BungeeCord.getInstance().getConsole().sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', datafile.getString("Messages.cspy-format"))
                        .replaceAll("%server%", p.getServer().getInfo().getName())
                        .replaceAll("%user%", p.getName())
                        .replaceAll("%command%", e.getMessage())
                        .replaceAll("%player%", PlayerHandler.playerwithrank(p))));
            }
        }
        if(e.getMessage().equalsIgnoreCase("/plugins") || e.getMessage().equalsIgnoreCase("/pl") || e.getMessage().equalsIgnoreCase("/bukkit:plugins")){
            ProxiedPlayer sender = (ProxiedPlayer) e.getSender();
            sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', datafile.getString("Messages.plugins"))
            ));
        }
        if(e.getMessage().equalsIgnoreCase("/help") || e.getMessage().equalsIgnoreCase("/?") || e.getMessage().equalsIgnoreCase("/bukkit:help") || e.getMessage().equalsIgnoreCase("/bukkit:?")){
            ProxiedPlayer sender = (ProxiedPlayer) e.getSender();
            sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', datafile.getString("Messages.help"))
            ));
        }
    }
}
