package net.badbird5907.aetheriacore.spigot.events;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class onPvp implements Listener {
    AetheriaCore plugin;
    public onPvp(AetheriaCore plugin) {
        this.plugin = plugin;
    }

    String allowpvp = (String) plugin.getConfig().get("allowpvp");
    @EventHandler
    public void onPvp(EntityDamageByEntityEvent event){
        if(event.getEntity().getType() == EntityType.PLAYER){
            if(event.getDamager().getType() == EntityType.PLAYER){
                if(plugin.getDataFile().getBoolean("pvp")){
                    return;
                }
                else{
                    event.setCancelled(true);

                }
            }

        }

    }
}
