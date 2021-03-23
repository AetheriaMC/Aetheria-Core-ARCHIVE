package net.badbird5907.aetheriacore.spigot.commands.impl.trolls;

import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class timeout implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player target = Bukkit.getPlayerExact(args[0]);
        if(sender.hasPermission(permissionManager.timeout)){
            target.kickPlayer("Timed Out");
        }
        return true;
    }
}
