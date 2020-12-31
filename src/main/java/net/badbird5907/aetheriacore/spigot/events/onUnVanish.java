package net.badbird5907.aetheriacore.spigot.events;

import de.myzelyam.api.vanish.PlayerShowEvent;
import net.badbird5907.aetheriacore.spigot.manager.PluginManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class onUnVanish implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onVanish(PlayerShowEvent event) {
        Player player = event.getPlayer();
        if(PluginManager.VanishedPlayers.contains(player.getName())){
            PluginManager.VanishedPlayers.remove(player.getName());
            if(PluginManager.OnlineVisiblePlayers.contains(player.getName())){
                PluginManager.warn(PluginManager.prefix + "Player is somehow vaniahed but listed as unvanished on AEC. please report this to Badbird5907.");
            }
            else {
                PluginManager.OnlineVisiblePlayers.add(player.getName());
            }
        }
    }
}
