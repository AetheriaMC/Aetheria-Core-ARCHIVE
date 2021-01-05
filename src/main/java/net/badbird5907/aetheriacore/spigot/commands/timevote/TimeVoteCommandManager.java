package net.badbird5907.aetheriacore.spigot.commands.timevote;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.commands.timevote.subcommands.No;
import net.badbird5907.aetheriacore.spigot.commands.timevote.subcommands.Yes;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

import static net.badbird5907.aetheriacore.spigot.AetheriaCore.getInstance;
import static net.badbird5907.aetheriacore.spigot.commands.timevote.ITimeVoteCmdManager.Process;

public class TimeVoteCommandManager implements CommandExecutor {
	public static ArrayList<TimeVote> commands = new ArrayList<>();
	public static ArrayList<String> commandstr = new ArrayList<>();
	public static HashMap<String, TimeVote> TimeVoteCommands = new HashMap<>();
	private final AetheriaCore plugin = getInstance();

	public TimeVoteCommandManager() {
	}

	public static void setup() {
		TimeVoteCommands.put("yes", new Yes());
		TimeVoteCommands.put("no", new No());
		TimeVoteCommands.forEach((k, v) -> {
			commands.add(v);
			commandstr.add(k);
		});
	}

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
		Process(sender, command, s, args);
		return true;
	}
}