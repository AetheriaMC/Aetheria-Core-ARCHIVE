package net.badbird5907.aetheriacore.spigot.events;

import net.badbird5907.aetheriacore.spigot.features.jukebox.CommandMusic;
import net.badbird5907.aetheriacore.spigot.features.jukebox.JukeBoxInventory;
import net.badbird5907.aetheriacore.spigot.features.jukebox.PlayerData;
import net.badbird5907.aetheriacore.spigot.features.jukebox.utils.Playlists;
import net.badbird5907.aetheriacore.spigot.setup.Noteblock;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class NoteblockListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Noteblock.datas.joins(e.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Noteblock.datas.quits(e.getPlayer());
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getItem() == null) return;
        if (Noteblock.jukeboxItem != null && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            if (e.getItem().equals(Noteblock.jukeboxItem)) {
                CommandMusic.open(e.getPlayer());
                e.setCancelled(true);
                return;
            }
        }
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && Noteblock.jukeboxClick) {
            if (e.getClickedBlock().getType() == Material.JUKEBOX) {
                String disc = e.getItem().getType().name();
                if (Noteblock.version < 13 ? JukeBoxInventory.discs8.contains(disc) : JukeBoxInventory.discs13.contains(disc)) {
                    CommandMusic.open(e.getPlayer());
                    e.setCancelled(true);
                    return;
                }
            }
        }
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent e) {
        if (!Noteblock.worlds) return;
        if (e.getFrom().getWorld() == e.getTo().getWorld()) return;
        if (Noteblock.worldsEnabled.contains(e.getTo().getWorld().getName())) return;
        PlayerData pdata = Noteblock.datas.getDatas(e.getPlayer());
        if (pdata == null) return;
        if (pdata.songPlayer != null) pdata.stopPlaying(true);
        if (pdata.getPlaylistType() == Playlists.RADIO) pdata.setPlaylist(Playlists.PLAYLIST, false);
    }
}
