package net.badbird5907.aetheriacore.spigot.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class onEndermanPickup implements Listener {
    @EventHandler
    public void onEndermanPickup (EntityChangeBlockEvent event) {
        Entity entity = event.getEntity();
        if (entity.getType().equals(EntityType.ENDERMAN)) {
            event.setCancelled(true);    //Cancelling the event
        }
    }
}