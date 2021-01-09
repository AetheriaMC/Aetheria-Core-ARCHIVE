package net.badbird5907.aetheriacore.spigot.events;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.commands.utils.hush;
import net.badbird5907.aetheriacore.spigot.manager.PluginManager;
import net.badbird5907.aetheriacore.spigot.util.Backup;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;

public class PlayerLeaveEvent implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void LeaveEvent (PlayerQuitEvent event){
        Backup.backup(event.getPlayer().getDisplayName(), event.getPlayer().getUniqueId(), new File(AetheriaCore.instance.getDataFolder().getAbsolutePath() + "/backups/playerdata"));
        Player player = event.getPlayer();
        if(hush.hush.contains(player.getName())){
            hush.hush.remove(player.getName());
        }
        else{
            return;
        }
        if(PluginManager.OnlinePlayers.contains(player.getName())){
            PluginManager.OnlinePlayers.remove(player.getName());
        }
        else{
            throw new IllegalStateException("Array List \"OnlinePlayers\" does not contain " + player.getDisplayName() + "did the server reload?");
        }
    }
}
