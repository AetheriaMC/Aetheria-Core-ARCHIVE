package net.badbird5907.aetheriacore.bungee.listeners;

import net.badbird5907.aetheriacore.bungee.util.Messages;
import net.badbird5907.aetheriacore.bungee.util.Permission;
import net.badbird5907.aetheriacore.bungee.util.PlayerHandler;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.event.EventHandler;

public class Login_Disconnect implements Listener {
    @EventHandler
    public void onDisconnect(PlayerDisconnectEvent e) {
        ProxiedPlayer p = e.getPlayer();
        Configuration config = Messages.getConfig("bungeemessages");
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
        Configuration config = Messages.getConfig("bungeemessages");
        if (p.hasPermission(Permission.BROADCAST_JOIN.node) &&
                config.getBoolean("Config.enable-join-message"))
            for (ProxiedPlayer staff : BungeeCord.getInstance().getPlayers()) {
                if (staff.hasPermission(Permission.STAFF_CHAT.node))
                    staff.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.staff-join-network")
                            .replaceAll("%player%", PlayerHandler.playerwithrank(p)))));
            }
        if(p.hasPermission(Permission.STAFF.node)){
            /*
            try {
                PreparedStatement ps = Database.getConnection().prepareStatement("SELECT * FROM AetheriaCoreBungee_Staff");
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    return;
                }
                else{
                    PreparedStatement ps1 = Database.getConnection().prepareStatement("INSERT INTO AetheriaCoreBungee_Staff VALUES (" + p.getUniqueId() + ", 0, 0, 0, 0); ");
                    ps1.executeQuery();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
             */
        }
    }
}
