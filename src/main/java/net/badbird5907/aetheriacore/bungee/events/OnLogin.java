package net.badbird5907.aetheriacore.bungee.events;

import net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee;
import net.badbird5907.aetheriacore.bungee.util.Permission;
import net.badbird5907.aetheriacore.bungee.util.PlayerHandler;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class OnLogin implements Listener {
    @EventHandler
    public void onLogin(PostLoginEvent e) {
        ProxiedPlayer p = e.getPlayer();
        if (p.hasPermission(Permission.BROADCAST_JOIN.node)) {
            for (ProxiedPlayer staff : ProxyServer.getInstance().getPlayers()) {
                if (staff.hasPermission(Permission.SEE_JOIN.node)) {
                    if (!AetheriaCoreBungee.Hush.contains(staff.getUniqueId())) {
                        staff.sendMessage(new TextComponent(ChatColor.GREEN + "Staff " + ChatColor.DARK_GRAY + "» " + PlayerHandler.playerwithrank(p) + ChatColor.GREEN + " Joined."));
                    }
                    else break;
                }
                //staff.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.staff-leave-network").replaceAll("%player%", p.getName()))));
            }
        }
    }


}
