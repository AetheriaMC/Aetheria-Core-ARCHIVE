package net.badbird5907.aetheriacore.spigot.auth.listeners;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.auth.TwoFactorAuthentication;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnPlayerConnection implements Listener {

    private final AetheriaCore main;
    public OnPlayerConnection(AetheriaCore main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        TwoFactorAuthentication.getInstance().getAuthHandler().playerJoin(player.getUniqueId());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        TwoFactorAuthentication.getInstance().getAuthHandler().playerQuit(player.getUniqueId());
    }
}
