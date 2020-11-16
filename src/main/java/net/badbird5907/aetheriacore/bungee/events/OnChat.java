package net.badbird5907.aetheriacore.bungee.events;

import net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee;
import net.badbird5907.aetheriacore.bungee.util.Permission;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class OnChat implements Listener {
    @EventHandler
    public void onChat(ChatEvent e) {
        if (!(e.getSender() instanceof ProxiedPlayer))
            return;
        if (e.getMessage().startsWith("/"))
            return;
        ProxiedPlayer p = (ProxiedPlayer)e.getSender();
        if (AetheriaCoreBungee.StaffChatPlayers.contains(p.getUniqueId())) {
            e.setCancelled(true);
            for (ProxiedPlayer staff : ProxyServer.getInstance().getPlayers()) {
                if (staff.hasPermission(Permission.STAFF_CHAT.node)) {
                    BaseComponent[] cp = TextComponent.fromLegacyText(ChatColor.GOLD + "StaffChat" + ChatColor.DARK_GRAY + " » (" + p.getServer().getInfo().getName() + ") " + ChatColor.RESET + p.getName() + ": " + e.getMessage());
                    if(AetheriaCoreBungee.Hush.contains(staff.getUniqueId()))
                        break;
                    else
                        staff.sendMessage(cp);
                }
            }
        }
        if (AetheriaCoreBungee.AdminChatPlayers.contains(p.getUniqueId())) {
            e.setCancelled(true);
            for (ProxiedPlayer staff : ProxyServer.getInstance().getPlayers()) {
                if (staff.hasPermission(Permission.ADMIN_CHAT.node)) {
                    BaseComponent[] cp = TextComponent.fromLegacyText(ChatColor.RED + "AdminChat" + ChatColor.DARK_GRAY + " » (" + p.getServer().getInfo().getName() + ") " + ChatColor.RESET + p.getName() + ": " + e.getMessage());
                    if(AetheriaCoreBungee.Hush.contains(staff.getUniqueId()))
                        break;
                    else
                        staff.sendMessage(cp);
                }
            }
        }
    }
}
