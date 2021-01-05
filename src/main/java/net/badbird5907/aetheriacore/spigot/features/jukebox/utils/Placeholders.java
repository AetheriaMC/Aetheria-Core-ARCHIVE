package net.badbird5907.aetheriacore.spigot.features.jukebox.utils;

import com.xxmicloxx.NoteBlockAPI.model.Song;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.badbird5907.aetheriacore.spigot.features.jukebox.PlayerData;
import org.bukkit.OfflinePlayer;

import java.util.List;

import static java.util.Arrays.asList;
import static net.badbird5907.aetheriacore.spigot.AetheriaCore.getInstance;
import static net.badbird5907.aetheriacore.spigot.features.jukebox.utils.Lang.*;
import static net.badbird5907.aetheriacore.spigot.setup.Noteblock.*;

public class Placeholders extends PlaceholderExpansion {

	private Placeholders() {
	}

	public static void registerPlaceholders() {
		new Placeholders().register();
		getInstance().getLogger().info("Placeholders registered");
	}

	@Override
	public String getAuthor() {
		return getInstance().getDescription().getAuthors().toString();
	}

	@Override
	public String getIdentifier() {
		return "jukebox";
	}

	@Override
	public String getVersion() {
		return getInstance().getDescription().getVersion();
	}

	@Override
	public boolean canRegister() {
		return true;
	}

	@Override
	public List<String> getPlaceholders() {
		return asList("playeroptions_volume", "playeroptions_shuffle", "playeroptions_join", "playeroptions_particles", "playeroptions_loop", "active", "active_title", "active_author", "active_description", "playlist");
	}

	@Override
	public String onRequest(OfflinePlayer p, String params) {
		PlayerData pdata = datas.getDatas(p.getUniqueId());
		if (pdata == null) return "§c§lunknown player data";
		if (params.startsWith("playeroptions_")) switch (params.substring(params.indexOf("_") + 1)) {
			case "volume":
				return pdata.getVolume() + "%";
			case "shuffle":
				return pdata.isShuffle() ? ENABLED : DISABLED;
			case "join":
				return pdata.hasJoinMusic() ? ENABLED : DISABLED;
			case "particles":
				return pdata.hasParticles() ? ENABLED : DISABLED;
			case "loop":
				return pdata.isRepeatEnabled() ? ENABLED : DISABLED;
			default:
				return "§c§lunknown option";
		}
		else if (params.startsWith("active")) {
			Song song;
			if (pdata.songPlayer == null) if (pdata.getPlaylistType() == Playlists.RADIO) song = radio.getSong();
			else return NONE;
			else song = pdata.songPlayer.getSong();
			if (params.equals("active_title")) return song.getTitle();
			if (params.equals("active_author")) return song.getAuthor();
			if (params.equals("active_description")) return song.getDescription();
			return getSongName(song);
		} else if (params.equals("playlist")) return pdata.getPlaylistType().name;
		return null;
	}
}