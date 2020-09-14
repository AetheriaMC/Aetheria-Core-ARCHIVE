package net.badbird5907.aetheriacore.spigot.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreeperPowerEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class onCreeperExplosion implements Listener {
    public void onCreeperExplosion (EntityExplodeEvent event) {
        Entity entity = event.getEntity();
        if(entity.getType().equals(EntityType.CREEPER)){
            event.setYield(1);
        }
    }
}
