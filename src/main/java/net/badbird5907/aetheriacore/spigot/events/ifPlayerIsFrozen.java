package net.badbird5907.aetheriacore.spigot.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import net.badbird5907.aetheriacore.spigot.commands.freezePlayer;

public class ifPlayerIsFrozen implements Listener {
    
    freezePlayer FP;
    public ifPlayerIsFrozen(freezePlayer FP) {
        this.FP = FP;
    }

    @EventHandler
    public void playerFrozen(PlayerMoveEvent event){
        Player player = event.getPlayer();

        }
    }
