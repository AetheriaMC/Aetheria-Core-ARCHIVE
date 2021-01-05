package net.badbird5907.aetheriacore.spigot.commands.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;
import static net.badbird5907.aetheriacore.spigot.manager.DebugLogger.DebugLog;
import static net.badbird5907.aetheriacore.spigot.manager.Permission.LOOP;
import static net.badbird5907.aetheriacore.spigot.util.IsInt.Check;
import static org.bukkit.Bukkit.*;

public class Loop implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
		DebugLog("Args: " + Arrays.toString(args));
		if (sender instanceof Player) {
			Player player = getPlayerExact(sender.getName());
			if (sender.hasPermission(LOOP.node)) {
				DebugLog("a1");
				DebugLog("a2");
				DebugLog("a3");
				DebugLog("Args length is " + args.length);
				if (args.length < 1) {
					DebugLog("a4");
					if (Check(args[0])) {
						DebugLog("a5");
						DebugLog("a6");
						range(0, parseInt(args[1])).forEach(i -> dispatchCommand(player, range(1, args.length).mapToObj(i1 -> args[i1] + " ").collect(joining()).trim()));
					}
				}
			}
		} else if (sender.hasPermission(LOOP.node) && args.length < 1) if (Check(args[0]))
			range(0, parseInt(args[1])).forEach(i -> dispatchCommand(getConsoleSender(), range(1, args.length).mapToObj(i1 -> args[i1] + " ").collect(joining()).trim()));
		return true;
	}
}