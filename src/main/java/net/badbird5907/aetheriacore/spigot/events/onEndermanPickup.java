package net.badbird5907.aetheriacore.spigot.events;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class onEndermanPickup implements Listener {
    AetheriaCore plugin;
    public onEndermanPickup(AetheriaCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEndermanPickup (EntityChangeBlockEvent event) {
        Entity entity = event.getEntity();
        if (entity.getType().equals(EntityType.ENDERMAN)) {
            if(plugin.getConfig().getBoolean("disable-enderman-pickup"))
                event.setCancelled(true);
        }
    }
}