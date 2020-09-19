package net.badbird5907.aetheriacore.spigot.events;

import de.myzelyam.api.vanish.VanishAPI;
import net.badbird5907.aetheriacore.spigot.manager.pluginManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerJoinEvent implements Listener {
    @EventHandler
    public void JoinEvent(PlayerLoginEvent event){
        Player player = event.getPlayer();
        if(VanishAPI.isInvisible(player) == false){
            if(pluginManager.OnlineVisiblePlayers.contains(event.getPlayer().getName())){
                pluginManager.warn(pluginManager.prefix + "Array List \"OnlinePlayers\" already contains " + event.getPlayer().getDisplayName());
            }
            else{
                pluginManager.OnlineVisiblePlayers.add(event.getPlayer().getName());
            }
        }
        else{
            pluginManager.VanishedPlayers.add(player.getName());
            pluginManager.OnlinePlayers.add(player.getName());
            pluginManager.warn("player is vanished.");
        }

    }
}
