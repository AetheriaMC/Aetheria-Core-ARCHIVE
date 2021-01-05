package net.badbird5907.aetheriacore.spigot.commands.timevote.subcommands;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.commands.timevote.TimeVote;
import org.bukkit.entity.Player;

import static net.badbird5907.aetheriacore.spigot.commands.timevote.VoteMgr.countvote;

public class No extends TimeVote {
	@Override
	public void execute(Player player, String[] args, String page, AetheriaCore plugin) {
		countvote(player, false);
	}
}