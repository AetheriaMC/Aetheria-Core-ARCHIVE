package net.badbird5907.aetheriacore.spigot.events;

import net.badbird5907.aetheriacore.spigot.manager.pluginManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerJoinEvent implements Listener {
    @EventHandler
    public void JoinEvent(PlayerLoginEvent event){
        if(pluginManager.OnlinePlayers.contains(event.getPlayer().getName())){
            pluginManager.warn("Array List \"OnlinePlayers\" already contains " + event.getPlayer().getDisplayName());
        }
        else{
            pluginManager.OnlinePlayers.add(event.getPlayer().getName());
        }
    }
}
