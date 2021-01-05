package net.badbird5907.aetheriacore.spigot.events;

import net.badbird5907.aetheriacore.spigot.features.jukebox.JukeBoxInventory;
import net.badbird5907.aetheriacore.spigot.features.jukebox.PlayerData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import static java.util.Objects.requireNonNull;
import static net.badbird5907.aetheriacore.spigot.features.jukebox.CommandMusic.open;
import static net.badbird5907.aetheriacore.spigot.features.jukebox.utils.Playlists.PLAYLIST;
import static net.badbird5907.aetheriacore.spigot.features.jukebox.utils.Playlists.RADIO;
import static net.badbird5907.aetheriacore.spigot.setup.Noteblock.*;
import static org.bukkit.Material.JUKEBOX;
import static org.bukkit.event.block.Action.RIGHT_CLICK_AIR;
import static org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK;

public class NoteblockListener implements Listener {
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		datas.joins(e.getPlayer());
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		datas.quits(e.getPlayer());
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getItem() == null) return;
		if ((jukeboxItem != null) && ((e.getAction() == RIGHT_CLICK_AIR) || (e.getAction() == RIGHT_CLICK_BLOCK)) && e.getItem().equals(jukeboxItem)) {
			open(e.getPlayer());
			e.setCancelled(true);
			return;
		}
		if ((e.getAction() == RIGHT_CLICK_BLOCK) && jukeboxClick && (requireNonNull(e.getClickedBlock()).getType() == JUKEBOX)) {
			String disc = e.getItem().getType().name();
			if (version < 13 ? JukeBoxInventory.discs8.contains(disc) : JukeBoxInventory.discs13.contains(disc)) {
				open(e.getPlayer());
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onTeleport(PlayerTeleportEvent e) {
		if (!worlds) return;
		if (e.getFrom().getWorld() == requireNonNull(e.getTo()).getWorld()) return;
		if (worldsEnabled.contains(requireNonNull(e.getTo().getWorld()).getName())) return;
		PlayerData pdata = datas.getDatas(e.getPlayer());
		if (pdata == null) return;
		if (pdata.songPlayer != null) pdata.stopPlaying(true);
		if (pdata.getPlaylistType() == RADIO) pdata.setPlaylist(PLAYLIST, false);
	}
}