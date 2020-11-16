package net.badbird5907.aetheriacore.bungee.events;

import net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee;
import net.badbird5907.aetheriacore.bungee.util.Permission;
import net.badbird5907.aetheriacore.bungee.util.PlayerHandler;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class OnSwitch implements Listener {
    @EventHandler
    public void onSwitch(ServerSwitchEvent e) {
        ProxiedPlayer p = e.getPlayer();
        if (p.hasPermission(Permission.BROADCAST_SWITCH.node)){
            for (ProxiedPlayer staff : ProxyServer.getInstance().getPlayers()) {
                if (staff.hasPermission(Permission.SEE_SWITCH.node) &&
                        e.getFrom() != null) {
                    String sserver = e.getFrom().getName().toString();
                    String dserver = e.getPlayer().getServer().getInfo().getName();
                    /*
                    staff.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.staff-leave-server").replaceAll("%server%", sserver).replaceAll("%player%", p.getName()))));
                    staff.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.staff-join-server").replaceAll("%server%", dserver).replaceAll("%player%", p.getName()))));
                    */
                    if(AetheriaCoreBungee.Hush.contains(staff.getUniqueId()))
                        break;
                    else
                        staff.sendMessage(new TextComponent(ChatColor.GREEN + "Staff " + ChatColor.DARK_GRAY + "» " + PlayerHandler.playerwithrank(p) + ChatColor.BLUE + " Switched from " + sserver + " To " + dserver));
                }
            }
        }
    }
}
