package net.badbird5907.aetheriacore.spigot.events;

import net.badbird5907.aetheriacore.spigot.commands.hush;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveEvent implements Listener {
    @EventHandler
    public void LeaveEvent (PlayerQuitEvent event){
        if(hush.hush.contains(event.getPlayer().getName())){
            hush.hush.remove(event.getPlayer().getName());
        }
        else{
            return;
        }
    }
}
