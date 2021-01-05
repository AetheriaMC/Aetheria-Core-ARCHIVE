package net.badbird5907.aetheriacore.spigot.features.jukebox.utils;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;
import static net.badbird5907.aetheriacore.spigot.manager.PluginManager.warn;
import static net.badbird5907.aetheriacore.spigot.setup.Noteblock.version;
import static net.md_5.bungee.api.ChatColor.translateAlternateColorCodes;
import static org.bukkit.ChatColor.*;


public class Lang {
	private static final char COLOR_CHAR = '\u00A7';
	public static String NEXT_PAGE = AQUA + "Next page";
	public static String LATER_PAGE = AQUA + "Previous page";
	public static String CURRENT_PAGE = DARK_AQUA + "§oPage %d of %d";
	public static String PLAYER = RED + "You must be a player to do this command.";
	public static String RELOAD_MUSIC = GREEN + "Music reloaded.";
	public static String INV_NAME = LIGHT_PURPLE + "§b§lMusic";
	public static String TOGGLE_PLAYING = GOLD + "Pause/play";
	public static String VOLUME = BLUE + "Music volume : §b";
	public static String RIGHT_CLICK = "§eRight click: decrease by 10%";
	public static String LEFT_CLICK = "§eLeft click: increase by 10%";
	public static String RANDOM_MUSIC = DARK_AQUA + "Random music";
	public static String STOP = RED + "Stop the music";
	public static String MUSIC_STOPPED = GREEN + "Music stopped.";
	public static String ENABLE = "Enable";
	public static String DISABLE = "Disable";
	public static String ENABLED = "Enabled";
	public static String DISABLED = "Disabled";
	public static String SHUFFLE_MODE = "the shuffle mode";
	public static String LOOP_MODE = "the loop mode";
	public static String CONNEXION_MUSIC = "music when connecting";
	public static String CONNECTION_MUSIC_ENABLE_DISABLE = "\n\nCurrently: ";
	public static String PARTICLES = "particles";
	public static String MUSIC_PLAYING = GREEN + "Playing Music: ";
	public static String INCORRECT_SYNTAX = RED + "Incorrect syntax.";
	public static String RELOAD_LAUNCH = GREEN + "Trying to reload.";
	public static String RELOAD_FINISH = GREEN + "Reload finished.";
	public static String AVAILABLE_COMMANDS = GREEN + "Available commands:";
	public static String INVALID_NUMBER = RED + "Invalid number.";
	public static String PLAYER_MUSIC_STOPPED = GREEN + "Music stopped for player: §b";
	public static String IN_PLAYLIST = BLUE + "§oIn Playlist";
	public static String PLAYLIST_ITEM = LIGHT_PURPLE + "Playlists";
	public static String OPTIONS_ITEM = AQUA + "Options";
	public static String MENU_ITEM = GOLD + "Return to menu";
	public static String CLEAR_PLAYLIST = RED + "Clear the current playlist";
	public static String NEXT_ITEM = YELLOW + "Next song";
	public static String CHANGE_PLAYLIST = GOLD + "§lSwitch playlist: §r";
	public static String CHANGE_PLAYLIST_LORE = YELLOW + "Middle-click on a music disc\n§e to add/remove the song";
	public static String PLAYLIST = DARK_PURPLE + "Playlist";
	public static String FAVORITES = DARK_RED + "Favorites";
	public static String RADIO = DARK_AQUA + "Radio";
	public static String UNAVAILABLE_RADIO = RED + "This action is unavailable while listening to the radio.";
	public static String NONE = RED + "none";

	public static void saveFile(YamlConfiguration cfg, File file) throws IllegalArgumentException, IllegalAccessException, IOException {
		for (Field f : Lang.class.getDeclaredFields()) {
			if (f.getType() != String.class) continue;
			if (!cfg.contains(f.getName())) cfg.set(f.getName(), f.get(null));
		}
		cfg.save(file);
	}

	public static void loadFromConfig(YamlConfiguration cfg) {
		for (String key : cfg.getValues(false).keySet())
			try {
				String str = cfg.getString(key);
				str = translateAlternateColorCodes('&', str);
				if (version >= 16) str = translateHexColorCodes("(&|§)#", "", str);
				Lang.class.getDeclaredField(key).set(key, str);
			} catch (Exception e) {
				warn("Error when loading language value \"" + key + "\".");
				e.printStackTrace();
			}
	}

	private static String translateHexColorCodes(String startTag, String endTag, String message) {
		final Pattern hexPattern = compile(startTag + "([A-Fa-f0-9]{6})" + endTag);
		Matcher matcher = hexPattern.matcher(message);
		StringBuffer buffer = new StringBuffer(message.length() + 4 * 8);
		while (matcher.find()) {
			String group = matcher.group(2);
			matcher.appendReplacement(buffer, COLOR_CHAR + "x" + COLOR_CHAR + group.charAt(0) + COLOR_CHAR + group.charAt(1) + COLOR_CHAR + group.charAt(2) + COLOR_CHAR + group.charAt(3) + COLOR_CHAR + group.charAt(4) + COLOR_CHAR + group.charAt(5));
		}
		return matcher.appendTail(buffer).toString();
	}
}