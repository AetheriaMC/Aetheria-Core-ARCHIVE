package net.badbird5907.aetheriacore.spigot.auth.listeners;

import net.badbird5907.aetheriacore.spigot.auth.events.PlayerStateChangeEvent;
import net.badbird5907.aetheriacore.shared.handlers.AuthHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class OnAuthStateChange implements Listener {

    @EventHandler
    public void onAuthState(PlayerStateChangeEvent event) {
        if(event.getAuthState().equals(AuthHandler.AuthState.AUTHENTICATED)) {
            event.getPlayer().setFlySpeed((float) 0.1);
            event.getPlayer().setWalkSpeed((float) 0.2);
        }
    }
}
