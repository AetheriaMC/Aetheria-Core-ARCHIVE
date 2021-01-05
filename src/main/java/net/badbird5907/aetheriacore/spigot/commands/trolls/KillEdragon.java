package net.badbird5907.aetheriacore.spigot.commands.trolls;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

import static java.util.Objects.requireNonNull;
import static org.bukkit.Bukkit.getPlayerExact;
import static org.bukkit.entity.EntityType.ENDER_DRAGON;

public class KillEdragon implements CommandExecutor {
	@Override
	public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
		requireNonNull(requireNonNull(getPlayerExact(commandSender.getName())).getLocation().getWorld()).getEntities().stream().filter(e -> e.getType().equals(ENDER_DRAGON)).forEach(Entity::remove);
		return true;
	}
}