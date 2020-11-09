package net.badbird5907.aetheriacore.spigot.customitems.LIGHTNING_ROD;

import net.badbird5907.aetheriacore.spigot.util.getBlock;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Lightning_Rod_Listener implements Listener {
    @EventHandler
    public void LightningRodListener1 (PlayerInteractEntityEvent event) {
        Player player = Bukkit.getPlayerExact(event.getPlayer().getDisplayName());
        if(player.getInventory().getItemInMainHand().getLore().contains("aetheria:LIGHTNING_ROD")){
            player.getWorld().strikeLightning(getBlock.getTargetBlock(player, 200));
        }
    }
    @EventHandler
    public void LightningRodListener2 (PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            player.getWorld().strikeLightning(event.getClickedBlock().getLocation());
        }
        if(player.getInventory().getItemInMainHand().getLore().contains("aetheria:LIGHTNING_ROD")){
            player.getWorld().strikeLightning(player.getEyeLocation());
        }
    }
}
