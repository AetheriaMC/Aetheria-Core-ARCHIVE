package net.badbird5907.aetheriacore.spigot.events;

import net.badbird5907.aetheriacore.spigot.commands.impl.utils.freezePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class BlockBreakEvent implements Listener {
    @EventHandler
    public void Blockbreakevent(org.bukkit.event.block.BlockBreakEvent event) {
        Player player = event.getPlayer();
        if(freezePlayer.frozen.contains(player.getUniqueId()))
            event.setCancelled(true);

    }
}
