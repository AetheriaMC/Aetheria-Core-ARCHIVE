package net.badbird5907.aetheriacore.bungee.events;

import net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee;
import net.badbird5907.aetheriacore.bungee.util.Permission;
import net.badbird5907.aetheriacore.bungee.util.PlayerHandler;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class OnDisconnect implements Listener {
    @EventHandler
    public void onDisconnect(PlayerDisconnectEvent e) {
        ProxiedPlayer p = e.getPlayer();
        if (p.hasPermission(Permission.BROADCAST_LEAVE.node)) {
            for (ProxiedPlayer staff : ProxyServer.getInstance().getPlayers()) {
                if (staff.hasPermission(Permission.SEE_LEAVE.node)) {
                    if (!AetheriaCoreBungee.Hush.contains(staff.getUniqueId())) {
                        staff.sendMessage(new TextComponent(ChatColor.GREEN + "Staff " + ChatColor.DARK_GRAY + "» " + PlayerHandler.playerwithrank(p) + ChatColor.GREEN + " Disconneced."));
                    }
                    else
                        break;
                }
                //staff.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.staff-leave-network").replaceAll("%player%", p.getName()))));
            }
        }
    }
}
