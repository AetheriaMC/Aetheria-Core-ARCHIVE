package net.badbird5907.aetheriacore.bungee.listeners;

import net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee;
import net.badbird5907.aetheriacore.bungee.api.SendAdminChatMessage;
import net.badbird5907.aetheriacore.bungee.api.SendStaffChatMessage;
import net.badbird5907.aetheriacore.bungee.util.Messages;
import net.badbird5907.aetheriacore.bungee.util.Permission;
import net.badbird5907.aetheriacore.bungee.util.PlayerHandler;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.event.EventHandler;

public class events implements Listener {
    @EventHandler
    public void onChat(ChatEvent e) {
        if (!(e.getSender() instanceof ProxiedPlayer))
            return;
        if (e.getMessage().startsWith("/"))
            return;
        ProxiedPlayer p = (ProxiedPlayer)e.getSender();
        Configuration config = Messages.getConfig("bungeemessages");
        if (AetheriaCoreBungee.inSc.contains(p.getUniqueId())) {
            e.setCancelled(true);
            SendStaffChatMessage.Send(p, e.getMessage());
        }
        if (AetheriaCoreBungee.inAc.contains(p.getUniqueId())) {
            e.setCancelled(true);
            SendAdminChatMessage.Send(p, e.getMessage());
        }
        if(e.getMessage().startsWith("#")){
            e.setCancelled(true);
            String message = e.getMessage().replaceFirst("#", "");
            SendStaffChatMessage.Send(p, message);
        }
    }

    @EventHandler
    public void onChat2(ChatEvent e) {
        ProxiedPlayer p = (ProxiedPlayer)e.getSender();
        Configuration config = Messages.getConfig("bungeemessages");
        if (p.hasPermission(Permission.COMMAND_SPY_BYPASS.node))
            return;
        if (e.getMessage().startsWith("/") && !e.getMessage().startsWith("/commandspy") && !e.getMessage().startsWith("/cspy")) {
            for (ProxiedPlayer staff : BungeeCord.getInstance().getPlayers()) {
                if (AetheriaCoreBungee.inCSpy.contains(staff.getUniqueId()))
                    staff.sendMessage(
                            new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.cspy-format"))
                                    .replaceAll("%server%", p.getServer().getInfo().getName())
                                    .replaceAll("%user%", p.getName())
                                    .replaceAll("%command%", e.getMessage())
                                    .replaceAll("%player%", PlayerHandler.playerwithrank(p))));
            }
            BungeeCord.getInstance().getConsole().sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.cspy-format"))
                    .replaceAll("%server%", p.getServer().getInfo().getName())
                    .replaceAll("%user%", p.getName())
                    .replaceAll("%command%", e.getMessage())
                    .replaceAll("%player%", PlayerHandler.playerwithrank(p))));
        }
    }

    @EventHandler
    public void onSwitch(ServerSwitchEvent e) {
        ProxiedPlayer p = e.getPlayer();
        Configuration config = Messages.getConfig("bungeemessages");
        if (p.hasPermission(Permission.BROADCAST_SWITCH.node) &&
                config.getBoolean("Config.enable-switch-messages"))
            for (ProxiedPlayer staff : BungeeCord.getInstance().getPlayers()) {
                if (staff.hasPermission(Permission.STAFF_CHAT.node) &&
                        e.getFrom() != null) {
                    String sserver = e.getFrom().getName();
                    String dserver = e.getPlayer().getServer().getInfo().getName();
                    staff.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.staff-switch-server")
                            .replaceAll("%from%", sserver)
                            .replaceAll("%to%", dserver)
                            .replaceAll("%player%", PlayerHandler.playerwithrank(p)))));
                }
            }
    }
}