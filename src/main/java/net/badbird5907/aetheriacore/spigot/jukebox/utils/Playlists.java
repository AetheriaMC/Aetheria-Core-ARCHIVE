package net.badbird5907.aetheriacore.spigot.jukebox.utils;

import net.badbird5907.aetheriacore.spigot.jukebox.JukeBoxInventory;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.stream.IntStream;

import static java.util.stream.IntStream.*;
import static net.badbird5907.aetheriacore.spigot.jukebox.JukeBoxInventory.radioItem;
import static net.badbird5907.aetheriacore.spigot.jukebox.utils.Lang.CHANGE_PLAYLIST;
import static net.badbird5907.aetheriacore.spigot.jukebox.utils.Lang.CHANGE_PLAYLIST_LORE;
import static org.bukkit.Material.JUKEBOX;
import static org.bukkit.Material.NOTE_BLOCK;

public enum Playlists {

	PLAYLIST(Lang.PLAYLIST, item(JUKEBOX, Lang.PLAYLIST)), FAVORITES(Lang.FAVORITES, item(NOTE_BLOCK, Lang.FAVORITES)), RADIO(Lang.RADIO, radioItem);

	public final ItemStack item;
	public final String name;

	private Playlists(String name, ItemStack item) {
		this.item = item;
		this.name = name;
	}

	public static int indexOf(Playlists playlist) {
		return range(0, values().length).filter(i -> values()[i] == playlist).findFirst().orElse(-1);
	}

	public static ItemStack item(Material material, String name) {
		return JukeBoxInventory.item(material, CHANGE_PLAYLIST + name, CHANGE_PLAYLIST_LORE.split("\n"));
	}

}