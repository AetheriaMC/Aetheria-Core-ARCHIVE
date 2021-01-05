package net.badbird5907.aetheriacore.spigot.commands.timevote;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import org.bukkit.entity.Player;

public abstract class TimeVote {
	public String name;
	public String info;
	public String[] aliases;

	/*
	/<command> args
	 */
	public TimeVote() {

	}

	public abstract void execute(Player player, String[] args, String page, AetheriaCore plugin);
}