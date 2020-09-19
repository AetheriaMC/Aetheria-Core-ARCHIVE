package net.badbird5907.aetheriacore.spigot.events;

import net.badbird5907.aetheriacore.spigot.commands.hush;
import net.badbird5907.aetheriacore.spigot.manager.pluginManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveEvent implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void LeaveEvent (PlayerQuitEvent event){
        Player player = event.getPlayer();
        if(hush.hush.contains(player.getName())){
            hush.hush.remove(player.getName());
        }
        else{
            return;
        }
        if(pluginManager.OnlinePlayers.contains(player.getName())){
            pluginManager.OnlinePlayers.remove(player.getName());
        }
        else{

            pluginManager.warn("Array List \"OnlinePlayers\" does not contain " + player.getDisplayName() + "did the server reload?");
        }
    }
}
