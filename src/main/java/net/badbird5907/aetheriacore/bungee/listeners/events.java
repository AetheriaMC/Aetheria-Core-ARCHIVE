package net.badbird5907.aetheriacore.bungee.listeners;

import net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee;
import net.badbird5907.aetheriacore.bungee.util.Permission;
import net.badbird5907.aetheriacore.bungee.util.PlayerHandler;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.event.EventHandler;
import org.w3c.dom.Text;

public class events implements Listener {
    @EventHandler
    public void onChat(ChatEvent e) {
        if (!(e.getSender() instanceof ProxiedPlayer))
            return;
        if (e.getMessage().startsWith("/"))
            return;
        ProxiedPlayer p = (ProxiedPlayer)e.getSender();
        Configuration config = AetheriaCoreBungee.getInstance().getConfig("bungeeconfig");
        if (AetheriaCoreBungee.inSc.contains(p.getUniqueId())) {
            e.setCancelled(true);
            for (ProxiedPlayer staff : BungeeCord.getInstance().getPlayers()) {
                if (staff.hasPermission(Permission.STAFF_CHAT.node)) {
                    TextComponent cp = new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.sc-format")
                            .replaceAll("%message%", e.getMessage())
                            .replaceAll("%player%", PlayerHandler.playerwithrank(p))
                            .replaceAll("%server%", p.getServer().getInfo().getName())));
                    cp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(
                            config.getString("Messages.sc-hover")
                    )));
                    staff.sendMessage(cp);
                }
            }
        }
        if (AetheriaCoreBungee.inAc.contains(p.getUniqueId())) {
            e.setCancelled(true);
            for (ProxiedPlayer staff : BungeeCord.getInstance().getPlayers()) {
                if (staff.hasPermission(Permission.ADMIN_CHAT.node)) {
                    BaseComponent[] cp = TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.ac-format")
                            .replaceAll("%message%", e.getMessage())
                            .replaceAll("%player%", PlayerHandler.playerwithrank(p))
                            .replaceAll("%server%", p.getServer().getInfo().getName())));
                    staff.sendMessage(cp);
                }
            }
        }
        if(e.getMessage().startsWith("#")){
            e.setCancelled(true);
            for (ProxiedPlayer staff : BungeeCord.getInstance().getPlayers()) {
                if (staff.hasPermission(Permission.STAFF_CHAT.node)) {
                    String message = e.getMessage().replaceFirst("#", "");
                    BaseComponent[] cp = TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.sc-format")
                            .replaceAll("%message%", message)
                            .replaceAll("%player%", PlayerHandler.playerwithrank(p))
                            .replaceAll("%server%", p.getServer().getInfo().getName())));

                    staff.sendMessage(cp);
                }
            }
        }
    }

    @EventHandler
    public void onChat2(ChatEvent e) {
        ProxiedPlayer p = (ProxiedPlayer)e.getSender();
        Configuration config = AetheriaCoreBungee.getInstance().getConfig("bungeeconfig");
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
    public void onDisconnect(PlayerDisconnectEvent e) {
        ProxiedPlayer p = e.getPlayer();
        Configuration config = AetheriaCoreBungee.getInstance().getConfig("bungeeconfig");
        if (p.hasPermission(Permission.BROADCAST_LEAVE.node) &&
                config.getBoolean("Config.enable-leave-message"))
            for (ProxiedPlayer staff : BungeeCord.getInstance().getPlayers()) {
                if (staff.hasPermission(Permission.STAFF_CHAT.node))
                    staff.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.staff-leave-network")
                            .replaceAll("%player%", PlayerHandler.playerwithrank(p)))));
            }
    }

    @EventHandler
    public void onLogin(PostLoginEvent e) {
        ProxiedPlayer p = e.getPlayer();
        Configuration config = AetheriaCoreBungee.getInstance().getConfig("bungeeconfig");
        if (p.hasPermission(Permission.BROADCAST_JOIN.node) &&
                config.getBoolean("Config.enable-join-message"))
            for (ProxiedPlayer staff : BungeeCord.getInstance().getPlayers()) {
                if (staff.hasPermission(Permission.STAFF_CHAT.node))
                    staff.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.staff-join-network")
                            .replaceAll("%player%", PlayerHandler.playerwithrank(p)))));
            }
    }

    @EventHandler
    public void onSwitch(ServerSwitchEvent e) {
        ProxiedPlayer p = e.getPlayer();
        Configuration config = AetheriaCoreBungee.getInstance().getConfig("bungeeconfig");
        if (p.hasPermission(Permission.BROADCAST_SWITCH.node) &&
                config.getBoolean("Config.enable-switch-messages"))
            for (ProxiedPlayer staff : BungeeCord.getInstance().getPlayers()) {
                if (staff.hasPermission(Permission.STAFF_CHAT.node) &&
                        e.getFrom() != null) {
                    String sserver = e.getFrom().getName().toString();
                    String dserver = e.getPlayer().getServer().getInfo().getName();
                    staff.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.staff-switch-server")
                            .replaceAll("%from%", sserver)
                            .replaceAll("%to%", dserver)
                            .replaceAll("%player%", PlayerHandler.playerwithrank(p)))));
                }
            }
    }
}