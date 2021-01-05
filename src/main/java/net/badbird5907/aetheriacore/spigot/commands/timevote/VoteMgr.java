package net.badbird5907.aetheriacore.spigot.commands.timevote;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import static java.lang.String.valueOf;
import static java.lang.System.currentTimeMillis;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static net.badbird5907.aetheriacore.spigot.manager.DebugLogger.DebugLog;
import static net.badbird5907.aetheriacore.spigot.manager.SoundManager.error;
import static net.badbird5907.aetheriacore.spigot.manager.SoundManager.ping;
import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;

public class VoteMgr {
	public static HashMap<UUID, Boolean> vote = new HashMap<>();
	public static ArrayList<UUID> voteyes = new ArrayList<>();
	public static ArrayList<UUID> voteno = new ArrayList<>();
	public static HashMap<UUID, Long> cooldown = new HashMap<>();
	//public static World first;
	static int cooldownTimeSeconds = 300;

	public static void countvote(Player player, boolean day) {
		if (canVote(player.getUniqueId())) {
			cooldown.put(player.getUniqueId(), currentTimeMillis());
			if (vote.containsKey(player.getUniqueId())) {
				error(player, 10);
				player.sendMessage(RED + "You already voted!");
			} else {
				vote.put(player.getUniqueId(), day);
				if (day) voteyes.add(player.getUniqueId());
				else voteno.add(player.getUniqueId());
				ping(player, 10);
				player.sendMessage(GREEN + "Success!");
			}
		} else {
			long seconds = MILLISECONDS.toSeconds(cooldown.get(player.getUniqueId()) - currentTimeMillis());
			long secondsleft = (cooldown.get(player.getUniqueId()) - currentTimeMillis() * 1000);
			player.sendMessage(RED + "Voting is on cooldown! You may vote only every 5 minutes.");
		}
	}

	public static boolean canVote(UUID player) {
		if (cooldown.containsKey(player)) {
			DebugLog(valueOf(cooldown.get(player) - (currentTimeMillis() * 1000)));
			if (cooldown.get(player) - currentTimeMillis() * 1000 < cooldownTimeSeconds) return false;
			else {
				cooldown.remove(player);
				return true;
			}
		}
		return true;
	}
}