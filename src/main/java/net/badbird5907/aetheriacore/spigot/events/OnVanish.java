package net.badbird5907.aetheriacore.spigot.events;

import de.myzelyam.api.vanish.PlayerHideEvent;
import net.badbird5907.aetheriacore.spigot.manager.PluginManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class OnVanish implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onVanish(PlayerHideEvent event) {
        Player player = event.getPlayer();
        PluginManager.OnlineVisiblePlayers.remove(player.getName());
        PluginManager.log(PluginManager.prefix + "Works");
    }
}
