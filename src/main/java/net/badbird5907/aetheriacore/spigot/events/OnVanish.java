package net.badbird5907.aetheriacore.spigot.events;

import de.myzelyam.api.vanish.PlayerHideEvent;
import net.badbird5907.aetheriacore.spigot.manager.pluginManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class OnVanish implements Listener {
    @EventHandler
    public void onVanish(PlayerHideEvent event) {
        Player player = event.getPlayer();
        pluginManager.log("works.");
    }
}
