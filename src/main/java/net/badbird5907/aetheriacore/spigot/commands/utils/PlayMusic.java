package net.badbird5907.aetheriacore.spigot.commands.utils;

import com.xxmicloxx.NoteBlockAPI.model.Song;
import net.badbird5907.aetheriacore.spigot.features.jukebox.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static java.lang.Integer.parseInt;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;
import static net.badbird5907.aetheriacore.spigot.features.jukebox.utils.Lang.INVALID_NUMBER;
import static net.badbird5907.aetheriacore.spigot.features.jukebox.utils.Playlists.PLAYLIST;
import static net.badbird5907.aetheriacore.spigot.manager.DebugLogger.DebugLog;
import static net.badbird5907.aetheriacore.spigot.setup.Noteblock.*;
import static org.bukkit.Bukkit.*;
import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;

public class PlayMusic implements CommandExecutor {
	/**
	 * @param who,id1
	 * @return String
	 */
	public static String play(String who, String id1) {
		Player cp = getPlayer(who);
		if (cp == null) return "§cUnknown player.";
		Song song;
		try {
			//get by id
			int id = parseInt(id1);
			try {
				DebugLog("Attempting to get " + id1 + " By ID.");
				song = getSongs().get(id);
			} catch (IndexOutOfBoundsException ex) {
				DebugLog(id1 + " Was not found.");
				return "§cError on §l" + id + " §r§c(inexistant)";
			}
		} catch (NumberFormatException ex) {
			//get by file name
			DebugLog("Attempting to get " + id1 + " By file.");
			song = getSongByFile(id1);
			if (song == null) {
				DebugLog(id1 + " Was not found.");
				return INVALID_NUMBER;
			}
		}
		DebugLog("a1");
		PlayerData pdata = datas.getDatas(cp);
		DebugLog("a2");
		pdata.setPlaylist(PLAYLIST, false);
		DebugLog("a3");
		pdata.playSong(song);
		DebugLog("a4");
		pdata.songPlayer.adminPlayed = true;
		return GREEN + "Playing...";
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
		if (args.length == 2) {
			if (args[0].equalsIgnoreCase("BROADCAST")) {
				for (Player p : getOnlinePlayers()) {
					String msg = play(p.getName(), args[1]);
					if (!msg.isEmpty()) sender.sendMessage(msg);
				}
			}
			if (args[0].equalsIgnoreCase("local")) {
				String msg = play(sender.getName(), range(2, args.length).mapToObj(i -> args[i] + " ").collect(joining()).trim());
				if (!msg.isEmpty()) sender.sendMessage(msg);
			}
			if (args[0].equalsIgnoreCase("player")) try {
				String msg = play(requireNonNull(getPlayerExact(args[1])).getName(), range(2, args.length).mapToObj(i -> args[i] + " ").collect(joining()).trim());
				if (!msg.isEmpty()) sender.sendMessage(msg);
			} catch (Exception e) {
				e.printStackTrace();
				sender.sendMessage(RED + args[1] + " is not an player.");
			}
		} else
			sender.sendMessage(RED + "Error: Usage: /playmusic <BROADCAST/LOCAL/PLAYER> <Player if PLAYER> <ID/FileName>");
		return true;
	}
}