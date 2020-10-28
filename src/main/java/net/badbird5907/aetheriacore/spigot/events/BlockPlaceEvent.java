package net.badbird5907.aetheriacore.spigot.events;

import net.badbird5907.aetheriacore.spigot.commands.utils.freezePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class BlockPlaceEvent implements Listener {
    @EventHandler
    public void BlockPlaceevent(org.bukkit.event.block.BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if(freezePlayer.frozen.contains(player.getUniqueId()))
            event.setCancelled(true);

    }
}
