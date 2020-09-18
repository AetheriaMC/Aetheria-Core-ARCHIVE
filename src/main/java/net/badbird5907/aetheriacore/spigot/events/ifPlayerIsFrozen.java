package net.badbird5907.aetheriacore.spigot.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import net.badbird5907.aetheriacore.spigot.commands.freezePlayer;

public class ifPlayerIsFrozen implements Listener {

    @EventHandler
    public void playerFrozen(PlayerMoveEvent event){
        Player player = event.getPlayer();
        if(freezePlayer.frozen.contains(player)){
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "You are currently frozen by a staff member.");
        }
        }
    }
