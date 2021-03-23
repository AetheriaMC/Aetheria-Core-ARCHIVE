package net.badbird5907.aetheriacore.spigot.commands.impl.trolls;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class KillEdragon implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = Bukkit.getPlayerExact(commandSender.getName());
        for (Entity e : player.getLocation().getWorld().getEntities()) {
            if(e.getType().equals(EntityType.ENDER_DRAGON))
                e.remove();
        }
        return true;
    }
}
