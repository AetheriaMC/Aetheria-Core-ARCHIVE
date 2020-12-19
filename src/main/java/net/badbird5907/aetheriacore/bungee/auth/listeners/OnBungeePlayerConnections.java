package net.badbird5907.aetheriacore.bungee.auth.listeners;

import net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class OnBungeePlayerConnections implements Listener {

    private final AetheriaCoreBungee main;
    public OnBungeePlayerConnections(AetheriaCoreBungee main) {
        this.main = main;
    }

    @EventHandler
    public void onJoin(LoginEvent event) {
        ProxiedPlayer player = ProxyServer.getInstance().getPlayer(event.getConnection().getUniqueId());

        if(player != null)
            AetheriaCoreBungee.getInstance().getAuthHandler().playerJoin(player.getUniqueId());
    }

    @EventHandler
    public void onQuit(PlayerDisconnectEvent event) {
        ProxiedPlayer player = event.getPlayer();
        AetheriaCoreBungee.getInstance().getAuthHandler().playerQuit(player.getUniqueId());
    }
}
