package net.badbird5907.aetheriacore.bungee.listeners;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.event.EventHandler;

import static java.util.Objects.requireNonNull;
import static net.badbird5907.aetheriacore.bungee.util.Messages.getConfig;
import static net.badbird5907.aetheriacore.bungee.util.Permission.*;
import static net.badbird5907.aetheriacore.bungee.util.PlayerHandler.playerwithrank;
import static net.md_5.bungee.api.ChatColor.translateAlternateColorCodes;

public class Login_Disconnect implements Listener {
	@EventHandler
	public void onDisconnect(PlayerDisconnectEvent e) {
		ProxiedPlayer p = e.getPlayer();
		Configuration config = getConfig("bungeemessages");
		if (p.hasPermission(BROADCAST_LEAVE.node) && requireNonNull(config).getBoolean("Config.enable-leave-message"))
			for (ProxiedPlayer staff : BungeeCord.getInstance().getPlayers())
				if (staff.hasPermission(STAFF_CHAT.node))
					staff.sendMessage(new TextComponent(translateAlternateColorCodes('&', config.getString("Messages.staff-leave-network").replaceAll("%player%", playerwithrank(p)))));
	}

	@EventHandler
	public void onLogin(PostLoginEvent e) {
		ProxiedPlayer p = e.getPlayer();
		Configuration config = getConfig("bungeemessages");
		if (p.hasPermission(BROADCAST_JOIN.node) && requireNonNull(config).getBoolean("Config.enable-join-message"))
			for (ProxiedPlayer staff : BungeeCord.getInstance().getPlayers())
				if (staff.hasPermission(STAFF_CHAT.node))
					staff.sendMessage(new TextComponent(translateAlternateColorCodes('&', config.getString("Messages.staff-join-network").replaceAll("%player%", playerwithrank(p)))));
		if (p.hasPermission(STAFF.node)) {
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