package net.badbird5907.aetheriacore.spigot.commands.timevote;

import static java.lang.Math.ceil;
import static net.badbird5907.aetheriacore.spigot.AetheriaCore.getInstance;
import static net.badbird5907.aetheriacore.spigot.commands.timevote.VoteMgr.*;
import static org.bukkit.Bukkit.*;

public class TimeMgr {
	public static int checks = 0;

	public static void checkVotes() {
		int voteyes = VoteMgr.voteyes.size();
		int voteno = VoteMgr.voteno.size();
		int halfonlineplayers = (int) ceil((double) getOnlinePlayers().size() / 2);
		checks++;
		if (((voteno + voteyes) < halfonlineplayers) || ((voteno + voteyes) == 0)) return;
		checks = 0;
		getWorlds().forEach(w -> w.setTime((voteno < voteyes) ? 6000L : 13000L));
	}

	public static void check() {
		getScheduler().scheduleSyncRepeatingTask(getInstance(), new Runnable() {
			@Override
			public void run() {
				checkVotes();
			}
		}, 0L, 20L); //1 second
	}

	public static void reset() {
		getScheduler().scheduleSyncRepeatingTask(getInstance(), new Runnable() {
			@Override
			public void run() {
				if (checks > 1) {
					voteno.clear();
					voteyes.clear();
					vote.clear();
					checks = 0;
				}
			}
		}, 0L, 24000L); //20 mins
	}

	public static void start() {
		checkVotes();
		check();
		reset();
	}
}